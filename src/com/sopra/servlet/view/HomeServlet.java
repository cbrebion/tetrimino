package com.sopra.servlet.view;

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

import com.sopra.dao.IPersonneDAO;
import com.sopra.exception.FormValidationException;
import com.sopra.model.Admin;
import com.sopra.model.Personne;

@WebServlet("/accueil")
public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static final String VUE_GET		= "/WEB-INF/accueil.jsp";
	private static final String VUE_ADMIN	= "admin/accueilAdmin";
//	private static final String VUE_JOUEUR	= "accueilJoueur";
	
	private static final String CHAMP_USER	= "username";
	private static final String CHAMP_PASS	= "password";
	
	private Map<String, String> erreurs		= new HashMap<String, String>();
	
	@Autowired
	private IPersonneDAO personneHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = getValeurChamp(req, CHAMP_USER);
		String password = getValeurChamp(req, CHAMP_PASS);
		
		erreurs.clear();
		
		try {
			validationUsername(username);
		} catch (FormValidationException e) {
			setErreurs(CHAMP_USER, e.getMessage());
		}
		
		try {
			validationPassword(password);
		} catch (FormValidationException e){
			setErreurs(CHAMP_PASS, e.getMessage());
		}
		
		// Si le formulaire est rempli
		if (erreurs.isEmpty()) {
			// Recherche de la personne
			Personne personne = personneHibernateDAO.findByUsername(username);
			
			if (personne != null) {
				if (personne.getPassword().equals(password)) {
					// ADMIN
					if (personne.getType() == 1) {
						Admin admin = (Admin) personne;
						req.getSession().setAttribute("admin", admin);
						resp.sendRedirect(VUE_ADMIN);
						return;
					}
					// JOUEUR
					else {
						setErreurs("connexion", "Vous n'êtes pas administrateur");
					}
				} else {
					setErreurs("connexion", "Mot de passe incorrect");
				}
			} else {
				setErreurs("connexion", "Vous n'êtes pas dans la base de données");
			}
		}
		
		req.setAttribute("erreurs", erreurs);
		req.setAttribute("valUsername", username);
		this.doGet(req, resp);
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
	
	
	private void validationUsername(String username) throws FormValidationException {
		if (username == null) {
			throw new FormValidationException("Merci de renseigner un nom d'utilisateur");
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
