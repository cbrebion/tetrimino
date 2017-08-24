package com.sopra.servlet.view;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IAdminDAO;
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
	
	@EJB(name="personneHibernateDAO")
	private IPersonneDAO personneHibernateDAO;
	
	@EJB(name="adminHibernateDAO")
	private IAdminDAO adminHibernateDAO;
	
//	@EJB(name="joueurHibernateDAO")
//	private IJoueurDAO joueurHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		//if (req.getSession().getAttribute(CHAMP_USER) == null) {
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
		/*} else {
			resp.sendRedirect(VUE_ADMIN);
		}*/
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
						//this.getServletContext().getRequestDispatcher(VUE_ADMIN).forward(req, resp);
						return;
					}
					// JOUEUR
					else {
						setErreurs("connexion", "Vous n'êtes pas administrateur");
//						Joueur joueur = (Joueur) personne;
//						req.getSession().setAttribute("joueur", joueur);
//						resp.sendRedirect(VUE_JOUEUR);
//						//this.getServletContext().getRequestDispatcher(VUE_JOUEUR).forward(req, resp);
//						return;
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
}
