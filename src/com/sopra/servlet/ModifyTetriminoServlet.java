package com.sopra.servlet;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.dao.server.TetriminoServerDAO;
import com.sopra.exception.FormValidationException;
import com.sopra.model.Tetrimino;

@WebServlet("/admin/modifPiece")
public class ModifyTetriminoServlet extends HttpServlet {
	@EJB(name="tetriminoHibernateDAO")
	private ITetriminoDAO tetriminoHibernateDAO;
	
	public static final String VUE_GET			= "/WEB-INF/admin/modifierTetrimino.jsp";
	public static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	private static final String PARAM_ID		= "id";
	
	private static final String CHAMP_ID		= "id";
	private static final String CHAMP_NOM		= "nom";
	private static final String CHAMP_COULEUR	= "couleur";
	private static final String ATT_TETRI		= "tetri";
	private static final String ATT_ERREUR		= "erreurs";
	
	private Map<String, String> erreurs		= new HashMap<String, String>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter(PARAM_ID));
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		req.setAttribute(ATT_TETRI, tetrimino);
		
		//this.getServletContext().getRequestDispatcher(VUE_GET + "?id=" + id).forward(req, resp);
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		erreurs.clear();
		
		String nom = getValeurChamp(req, CHAMP_NOM);
		String couleur = getValeurChamp(req, CHAMP_COULEUR);
		int id = Integer.parseInt(req.getParameter(CHAMP_ID));
		
		Tetrimino tetrimino = new Tetrimino();
		
		tetrimino.setId(id);
		
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_NOM, e.getMessage());
		}
		tetrimino.setNom(nom);
		
		try {
			validationCouleur(couleur);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_COULEUR, e.getMessage());
		}
		tetrimino.setCouleur(couleur);
		
		if (erreurs.isEmpty()) {
			tetriminoHibernateDAO.save(tetrimino);
			resp.sendRedirect(VUE_POST);
		} else {
			req.setAttribute(ATT_TETRI, tetrimino);
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
}
