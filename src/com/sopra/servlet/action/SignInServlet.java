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

import com.sopra.dao.IJoueurDAO;
import com.sopra.exception.FormValidationException;
import com.sopra.model.Joueur;

@WebServlet("/signin")
public class SignInServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	private static final String VUE_SIGNIN		= "/WEB-INF/views/jsp/signin.jsp";
	private static final String VUE_POST		= "accueil";
	
	private static final String CHAMP_NOM		= "nom";
	private static final String CHAMP_PRENOM	= "prenom";
	private static final String CHAMP_PASSWORD	= "password";
	private static final String CHAMP_USERNAME	= "username";
	
	private static final String ATT_JOUEUR		= "joueur";
	private static final String ATT_ERREUR		= "erreurs";
	
	private Map<String, String> erreurs			= new HashMap<String, String>();

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_SIGNIN).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		Joueur joueur = new Joueur();
		
		String username = getValeurChamp(req, CHAMP_USERNAME);
		String nom = getValeurChamp(req, CHAMP_NOM);
		String prenom = getValeurChamp(req, CHAMP_PRENOM);
		String password = getValeurChamp(req, CHAMP_PASSWORD);
		
		try {
			validationNom(nom);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_NOM, e.getMessage());
		}
		joueur.setNom(nom);
		
		try {
			validationPrenom(prenom);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_PRENOM, e.getMessage());
		}
		joueur.setPrenom(prenom);
		
		try {
			validationPassword(password);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_PASSWORD, e.getMessage());
		}
		joueur.setPassword(password);
		
		try {
			validationUsername(username);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_USERNAME, e.getMessage());
		}
		joueur.setUsername(username);
		
		
		if (erreurs.isEmpty()) {
			joueur = joueurHibernateDAO.save(joueur);
			req.getSession().setAttribute(ATT_JOUEUR, joueur);
			resp.sendRedirect(VUE_POST);
		} else {
			req.setAttribute(ATT_ERREUR, erreurs);
			this.getServletContext().getRequestDispatcher(VUE_SIGNIN).forward(req, resp);
		}
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
	
	private void validationPrenom(String prenom) throws FormValidationException {
		if (prenom == null) {
			throw new FormValidationException("Merci de renseigner un prénom");
		}
	}
	
	private void validationUsername(String username) throws FormValidationException {
		if (username == null) {
			throw new FormValidationException("Merci de renseigner un pseudo");
		}
	}
	
	private void validationPassword(String password) throws FormValidationException {
		if (password == null) {
			throw new FormValidationException("Merci de renseigner un mot de passe");
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
