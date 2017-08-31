package com.sopra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;

@Controller
public class JoueursController {

	private static final String ATT_JOUEURS		= "joueurs";
	
	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	@RequestMapping(value="listeJoueurs", method = RequestMethod.GET)
	public String listeJoueurs(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		List<Joueur> joueurs = joueurHibernateDAO.findAll();
		
		model.addAttribute(ATT_JOUEURS, joueurs);
		
		return "listeJoueurs";
	}
}
