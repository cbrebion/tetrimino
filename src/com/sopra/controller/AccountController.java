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

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;
import com.sopra.model.Personne;

@Controller
public class AccountController {
	private static final String ATT_JOUEUR	= "joueur";
	
	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	
	/**
	 * SIGN IN (GET)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signin", method=RequestMethod.GET)
	public String signIn(Model model) {
		return "signin";
	}
	
	
	/**
	 * SIGN IN (POST)
	 * @param p
	 * @param result
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/signin", method=RequestMethod.POST)
	public String signIn(@Valid @ModelAttribute("personne") Personne p,
			BindingResult result,
			HttpSession session) {
		
		if (result.hasErrors()) {
			return "signin";
		}
		
		Joueur joueur= new Joueur();
		joueur.setNom(p.getNom());
		joueur.setPrenom(p.getPrenom());
		joueur.setUsername(p.getUsername());
		joueur.setPassword(p.getPassword());
		
		joueur = joueurHibernateDAO.save(joueur);
		session.setAttribute(ATT_JOUEUR, joueur);
		
		return "redirect:/accueil";
	}
	
	
	/**
	 * DECONNEXION
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/deconnexion", method=RequestMethod.GET)
	public String deconnexion(HttpSession session) {
		session.invalidate();
		
		return "redirect:/accueil";
	}
	
	
	@ModelAttribute("personne")
	public Personne initPersonne() {
		Personne personne = new Personne();
		return personne;
	}
	
}
