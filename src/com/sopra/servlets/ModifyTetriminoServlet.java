package com.sopra.servlets;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.dao.server.TetriminoServerDAO;
import com.sopra.exception.FormValidationException;
import com.sopra.model.Tetrimino;

@WebServlet("/modifPiece")
public class ModifyTetriminoServlet extends HttpServlet {
	public static final String VUE_GET			= "/WEB-INF/modifierTetrimino.jsp";
	public static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	private static final String PARAM_ID		= "id";
	
	private static final String CHAMP_NOM		= "nom";
	private static final String CHAMP_COULEUR	= "couleur";
	private static final String ATT_TETRI		= "tetri";
	private static final String ATT_ERREUR		= "erreurs";
	
	private Map<String, String> erreurs		= new HashMap<String, String>();
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter(PARAM_ID));
		
		ITetriminoDAO tetriminoServerDAO = new TetriminoServerDAO();
		
		Tetrimino tetri = tetriminoServerDAO.rechercher(req, id);
		
		req.setAttribute(ATT_TETRI, tetri);
		
		this.getServletContext().getRequestDispatcher(VUE_GET + "?id=" + id).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = getValeurChamp(req, CHAMP_NOM);
		String couleur = getValeurChamp(req, CHAMP_COULEUR);
		
		ITetriminoDAO tetriminoServerDAO = new TetriminoServerDAO();
		
		Tetrimino tetri = (Tetrimino) req.getAttribute(ATT_TETRI);
		
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_NOM, e.getMessage());
		}
		tetri.setNom(nom);
		
		try {
			validationCouleur(couleur);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_COULEUR, e.getMessage());
		}
		tetri.setCouleur(couleur);
		
		if (erreurs.isEmpty()) {
			tetriminoServerDAO.modifier(req, tetri);
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
}
