package com.sopra.controller;

import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

@Controller
public class TetriminoController {
	private static final String ATT_TETRIMINO	= "tetrimino";
	private static final String ATT_TETRIMINOS	= "tetriminos";
	private static final String ATT_ERREUR		= "erreur";
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	/**
	 * TETRIMINO (GET)
	 * @param idTetrimino
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/tetrimino", method = RequestMethod.GET)
	public String tetrimino(@RequestParam(value="id", required=true) int idTetrimino,
			Model model) {
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		model.addAttribute(ATT_TETRIMINO, tetrimino);
		
		return "tetrimino";
	}
	
	/**
	 * LISTE TETRIMINOS (GET)
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/listeTetriminos", method = RequestMethod.GET)
	public String listeTetriminos(HttpSession session, Model model) {
		// Sécurité à cause de "ajoutFigure" (à modifier)
		session.removeAttribute("blocs");
		
		List<Tetrimino> tetriminos = tetriminoHibernateDAO.findAll();
		
		model.addAttribute(ATT_TETRIMINOS, tetriminos);
		
		return "listeTetriminos";
	}
	
	
	/**
	 * AJOUT TETRIMINO (GET)
	 * @return
	 */
	@RequestMapping(value="/ajoutTetrimino", method=RequestMethod.GET)
	public String ajoutTetrimino() {
		return "ajoutTetrimino";
	}
	
	
	/**
	 * AJOUT TETRIMINO (POST)
	 * @param tetrimino
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/ajoutTetrimino", method=RequestMethod.POST)
	public String ajoutTetrimino(@Valid @ModelAttribute("tetrimino") Tetrimino tetrimino,
			BindingResult result,
			Model model) {
		
		if (result.hasErrors()) {
			return "ajoutTetrimino";
		}
		
		tetrimino = tetriminoHibernateDAO.save(tetrimino);
		
		return "redirect:/listeTetriminos";
	}
	
	
	/**
	 * MODIFIER TETRIMINO (GET)
	 * @param idTetrimino
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modifTetrimino", method=RequestMethod.GET)
	public String modifierTetrimino(@RequestParam(value="id", required=true) int idTetrimino,
			Model model) {
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		model.addAttribute(ATT_TETRIMINO, tetrimino);
		
		return "modifTetrimino";
	}
	
	
	@RequestMapping(value="/modifTetrimino", method=RequestMethod.POST)
	public String modifierTetrimino(@Valid @ModelAttribute("tetrimino") Tetrimino t,
			BindingResult result,
			@RequestParam(value="idTetrimino") int idTetrimino,
			Model model) {
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		if (result.hasErrors()) {
			model.addAttribute(ATT_TETRIMINO, tetrimino);
			
			model.addAttribute(ATT_ERREUR, "Erreur dans la modification");
			
			return "modifTetrimino";
		}
		
		tetrimino.setNom(t.getNom());
		tetrimino.setCouleur(t.getCouleur());
		tetrimino.setCoeff(t.getCoeff());
		
		tetrimino = tetriminoHibernateDAO.save(tetrimino);
		
		return "redirect:/listeTetriminos";
	}
	
	
	/**
	 * SUPPRIMER TETRIMINO
	 * @param idTetrimino
	 * @return
	 */
	@RequestMapping(value="/supprimerTetrimino", method=RequestMethod.GET)
	public String supprimerTetrimino(@RequestParam(value="id", required=true) int idTetrimino) {
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		tetriminoHibernateDAO.delete(tetrimino);
		
		return "redirect:/listeTetriminos";
	}
	
	
	@ModelAttribute("tetrimino")
	public Tetrimino initFigure() {
		Tetrimino tetrimino = new Tetrimino();
		return tetrimino;
	}
}
