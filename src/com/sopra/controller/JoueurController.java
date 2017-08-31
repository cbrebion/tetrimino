package com.sopra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;

@Controller
public class JoueurController {

	private static final String ATT_JOUEURS		= "joueurs";
	
	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	/**
	 * LISTE JOUEURS (GET)
	 * @param session
	 * @return
	 */
	@RequestMapping(value="listeJoueurs", method = RequestMethod.GET)
	public String listeJoueurs(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		List<Joueur> joueurs = joueurHibernateDAO.findAll();
		
		model.addAttribute(ATT_JOUEURS, joueurs);
		
		return "listeJoueurs";
	}
	
	
	/**
	 * BANNIR (GET)
	 * @param idJoueur
	 * @return
	 */
	@RequestMapping(value="/bannir", method = RequestMethod.GET)
	public String bannir(@RequestParam(value="id", required = true) int idJoueur) {
		Joueur joueur = joueurHibernateDAO.find(idJoueur);
		
		joueur.setBanni(!joueur.getBanni());
		
		joueur = joueurHibernateDAO.save(joueur);
		
		return "redirect:/listeJoueurs";
	}
}
