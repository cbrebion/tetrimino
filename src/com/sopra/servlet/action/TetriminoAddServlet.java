package com.sopra.servlet.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.exception.FormValidationException;
import com.sopra.model.Tetrimino;

//@WebServlet("/ajoutTetrimino")
public class TetriminoAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_GET					= "/WEB-INF/views/jsp/ajouterTetrimino.jsp";
	private static final String VUE_POST				= "/tetrimino/listeTetriminos";
	
	private static final String ATT_NOM					= "nom";
	private static final String ATT_COULEUR				= "couleur";
	private static final String ATT_COEFF				= "coeff";
	private static final String ATT_ERREUR				= "erreurs";
	
	private Map<String, String> erreurs	= new HashMap<String, String>();
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		erreurs.clear();
		
		// Récupération des valeurs
		String nom = getValeurChamp(req, ATT_NOM);
		String couleur = getValeurChamp(req, ATT_COULEUR);
		double coeff = 0;
		
		// Création du tetrimino
		Tetrimino tetrimino = new Tetrimino();
		
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreurs(ATT_NOM, e.getMessage());
		}
		tetrimino.setNom(nom);
		
		try {
			validationCouleur(couleur);
		} catch (FormValidationException e) {
			setErreurs(ATT_COULEUR, e.getMessage());
		}
		tetrimino.setCouleur(couleur);
		
		try {
			coeff = Double.parseDouble(req.getParameter(ATT_COEFF));
		} catch (NumberFormatException nfe) {
			setErreurs(ATT_COEFF, "Le coeff doit être un nombre");
		}
		tetrimino.setCoeff(coeff);
		
		if (erreurs.isEmpty()) {
			tetriminoHibernateDAO.save(tetrimino);
			resp.sendRedirect(VUE_POST);
		} else {
			req.setAttribute(ATT_ERREUR, erreurs);
			this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
		}
	}
	
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	private void setErreurs(String champ, String message) {
		erreurs.put(champ, message);
	}

	
	// Méthode récupérant les valeurs des champs en faisant des petits tests dessus
	private static String getValeurChamp(HttpServletRequest request, String nomChamp) {
		String valeur = request.getParameter(nomChamp);

		if (valeur == null || valeur.trim().length() == 0) {
			return null;
		} else {
			return valeur.trim();
		}
	}
	
	
	private void validationNom(String nom) throws FormValidationException {
		if (nom == null) {
			throw new FormValidationException("Merci de renseigner un nom");
		}
	}
	
	private void validationCouleur(String couleur) throws FormValidationException {
		if (couleur == null) {
			throw new FormValidationException("Merci de renseigner une couleur");
		}
	}
	
	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}
}
