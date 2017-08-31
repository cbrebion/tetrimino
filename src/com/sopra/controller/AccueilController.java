package com.sopra.controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sopra.dao.IPersonneDAO;
import com.sopra.model.Admin;
import com.sopra.model.Personne;

@Controller
public class AccueilController {
	
	private static final String ATT_ADMIN	= "admin";
	private static final String ATT_ERREUR	= "erreur";
	
	@Autowired
	private IPersonneDAO personneHibernateDAO;
	
	@RequestMapping(value="/accueil", method = RequestMethod.GET)
	public String accueil(Model model) {
		return "accueil";
	}
	
	@RequestMapping(value="/accueil", method = RequestMethod.POST)
	public String accueil(@Valid @ModelAttribute("personne") Personne p,
			BindingResult result,
			HttpSession session,
			Model model) {
		
		if (result.hasErrors()) {
			
			return "accueil";
		}
		
		// Récupération de la personne par nom d'utilisateur
		Personne personne = personneHibernateDAO.findByUsername(p.getUsername());
		
		if (personne != null) {
			// Vérification mot de passe
			if (personne.getPassword().equals(p.getPassword())) {
				// Si c'est un admin
				if (personne.getType() == 1) {
					Admin admin = (Admin) personne;
					// Enregistrement en session de l'admin
					session.setAttribute(ATT_ADMIN, admin);
					
					return "redirect:/accueilAdmin";
				}
				// Si c'est un joueur
				else {
					model.addAttribute(ATT_ERREUR, "Vous n'êtes pas administrateur");
					
					return "accueil";
				}
			}
			// MdP incorrect
			else {
				model.addAttribute(ATT_ERREUR, "Mot de passe incorrect");
				
				return "accueil";
			}
		}
		// Non inscrit
		else {
			model.addAttribute(ATT_ERREUR, "Vous n'êtes pas inscrit");
			
			return "accueil";
		}
	}
	
	
	@ModelAttribute("personne")
	public Personne initPersonne() {
		Personne personne = new Personne();
		// Pour éviter l'erreur du "nom NotEmpty"
		personne.setNom("default");
		personne.setPrenom("default");
		return personne;
	}
}
