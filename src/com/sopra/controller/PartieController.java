package com.sopra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;

@Controller
public class PartieController {
	private static final String ATT_PARTIES		= "parties";
	
	@Autowired
	private IPartieDAO partieHibernateDAO;
	
	
	/**
	 * LISTER PARTIES
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listeParties", method = RequestMethod.GET)
	public String listeParties(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		List<Partie> parties = partieHibernateDAO.findAllWithScores();
		
		model.addAttribute(ATT_PARTIES, parties);
		
		return "listeParties";
	}

}
