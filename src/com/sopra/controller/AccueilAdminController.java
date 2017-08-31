package com.sopra.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccueilAdminController {

	@RequestMapping(value="/accueilAdmin", method = RequestMethod.GET)
	public String accueilAdmin(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		return "accueilAdmin";
	}
}
