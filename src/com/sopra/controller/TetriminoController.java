package com.sopra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

@Controller
public class TetriminoController {
	private static final String PARAM_ID		= "id";
	private static final String ATT_TETRIMINO	= "tetrimino";
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@RequestMapping(value="/tetrimino", method = RequestMethod.GET)
	public String tetrimino(@RequestParam(value="id", required=true) int idTetrimino,
			Model model) {
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		model.addAttribute(ATT_TETRIMINO, tetrimino);
		
		return "tetrimino";
	}
}
