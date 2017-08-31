package com.sopra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

@Controller
public class TetriminosController {
	private static final String ATT_TETRIMINOS	= "tetriminos";
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@RequestMapping(value="/listeTetriminos", method = RequestMethod.GET)
	public String listeTetriminos(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		List<Tetrimino> tetriminos = tetriminoHibernateDAO.findAll();
		
		model.addAttribute(ATT_TETRIMINOS, tetriminos);
		
		return "listeTetriminos";
	}
}
