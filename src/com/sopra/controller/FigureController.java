package com.sopra.controller;

import java.util.ArrayList;
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

import com.sopra.dao.IBlocDAO;
import com.sopra.dao.IFigureDAO;
import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Bloc;
import com.sopra.model.Figure;
import com.sopra.model.Tetrimino;

@Controller
public class FigureController {
	private static final String ATT_TETRIMINO		= "tetrimino";
	private static final String ATT_TETRIMINO_ID	= "idTetrimino";
	private static final String ATT_FIGURE			= "figure";
	private static final String ATT_FIGURE_ID		= "idFigure";
	private static final String ATT_ERREUR			= "erreur";
	
	private static final String SESSION_BLOCS		= "blocs";
	
	private List<Bloc> blocs						= new ArrayList<Bloc>();
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@Autowired
	private IBlocDAO blocHibernateDAO;
	
	@Autowired
	private IFigureDAO figureHibernateDAO;
	
	
	/**
	 * AJOUTER FIGURE (GET)
	 * @param idTetrimino
	 * @param x
	 * @param y
	 * @param session
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ajoutFigure", method = RequestMethod.GET)
	public String ajoutFigure(@RequestParam(value="id", required=true) int idTetrimino,
			@RequestParam(value="x", required=false) Integer x,
			@RequestParam(value="y", required=false) Integer y,
			HttpSession session,
			Model model) {
		
		// Récupération de la liste des blocs déjà sélectionné
		if (session.getAttribute(SESSION_BLOCS) != null)
			blocs = (List<Bloc>) session.getAttribute(SESSION_BLOCS);
		else
			blocs = new ArrayList<Bloc>();
		
		// Récupération du tetrimino
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
				
		// S'il y a bien un paramètre x dans la reqûete
		if (x != null) {
			// Assignation des valeurs au bloc
			Bloc bloc = new Bloc();
			bloc.setX(x);
			bloc.setY(y);
			
			// Vérifie si le bloc n'a pas déjà été sélectionné
			int i = 0;
			int indexExiste = -1;
			for (Bloc blocCurrent : blocs) {
				if ((blocCurrent.getX() == bloc.getX()) && (blocCurrent.getY() == bloc.getY())) {
					indexExiste = i;
				}
				i++;
			}

			// Retire le bloc de la liste s'il a été désélectionné
			if (indexExiste != -1) {
				blocs.remove(blocs.get(indexExiste));
			}
			// Le rajoute sinon
			else {
				blocs.add(bloc);
			}
		}
		
		// Envoi de la liste des blocs en cours et du tetrimino
		session.setAttribute(SESSION_BLOCS, blocs);
		model.addAttribute(ATT_TETRIMINO, tetrimino);
		
		return "ajoutFigure";
	}
	
	
	/**
	 * AJOUT FIGURE (POST)
	 * @param f
	 * @param result
	 * @param idTetrimino
	 * @param session
	 * @param model
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value="/ajoutFigure", method = RequestMethod.POST)
	public String ajoutFigure(@Valid @ModelAttribute("figure") Figure f,
			BindingResult result,
			@RequestParam(value="id", required=true) int idTetrimino,
			HttpSession session,
			Model model) {
		
		// Récupération du tetrimino
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		
		// Récupération de la liste de blocs
		if (session.getAttribute(SESSION_BLOCS) != null) {
			blocs = (List<Bloc>) session.getAttribute(SESSION_BLOCS);
		}
		
		// Si une erreur existe dans le formulaire
		if (result.hasErrors()) {
			session.setAttribute(SESSION_BLOCS, blocs);
			model.addAttribute(ATT_TETRIMINO, tetrimino);
			model.addAttribute(ATT_TETRIMINO_ID, idTetrimino);
			
			return "ajoutFigure";
		}
	 
		// Si on tente de valider sans avoir cliqué sur un seul bloc
		if (blocs.size() == 0) {
			model.addAttribute(ATT_ERREUR, "Veuillez créer au moins une figure");
			session.setAttribute(SESSION_BLOCS, blocs);
			model.addAttribute(ATT_TETRIMINO, tetrimino);
			model.addAttribute(ATT_TETRIMINO_ID, idTetrimino);
			return "ajoutFigure";
		}
		
		
		// Création et initialisation de la figure
		Figure figure = new Figure();
		figure.setTetrimino(tetrimino);
		figure.setOrdreRotation(f.getOrdreRotation());
		figure.setBlocs(blocs);
		
		figure = figureHibernateDAO.save(figure);
		
		// Affectation de chaque bloc à la figure + enregistrement des blocs
		for (Bloc bloc : blocs) {
			bloc.setFigure(figure);
			bloc = blocHibernateDAO.save(bloc);
		}
		
		// Reset de la liste de blocs
		blocs.clear();
		session.removeAttribute(SESSION_BLOCS);
		
		return "redirect:/tetrimino?id=" + idTetrimino;
	}
	
	
	/**
	 * MODIFIER FIGURE (GET)
	 * @param idTetrimino
	 * @param idFigure
	 * @param x
	 * @param y
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modifFigure", method=RequestMethod.GET)
	public String modifFigure(@RequestParam(value="idTetrimino", required=true) int idTetrimino,
			@RequestParam(value="idFigure", required=true) int idFigure,
			@RequestParam(value="x", required=false) Integer x,
			@RequestParam(value="y", required=false) Integer y,
			Model model) {
		
		Bloc blocToDelete;
		
		// Récupération du tetrimino et de la figure à modifier
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		Figure figure = figureHibernateDAO.find(idFigure);
		
		// Si on a cliqué sur un des blocs
		if (x != null) {
			Bloc bloc = new Bloc();
			bloc.setX(x);
			bloc.setY(y);
			bloc.setFigure(figure);
			
			// Récupération de la liste des blocs liés à la figure
			List<Bloc> blocs = figure.getBlocs();
			
			// Vérifie si le bloc n'a pas déjà été sélectionné
			int i = 0;
			int indexExiste = -1;
			for (Bloc blocCurrent : blocs) {
				// Désélection
				if ((blocCurrent.getX() == bloc.getX()) && (blocCurrent.getY() == bloc.getY())) {
					indexExiste = i;
					// Supprime le bloc de la DB
					blocToDelete = blocHibernateDAO.find(blocCurrent.getId());
					blocHibernateDAO.delete(blocToDelete);
				}
				i++;
			}

			// Si le bloc existe déjà (désélection)
			if (indexExiste != -1) {
				// Suppression du bloc présent dans la liste de blocs de la figure
				blocs.remove(blocs.get(indexExiste));
			}
			// Sinon on le rajoute (sélection)
			else {
				// Ajout en DB + dans la liste des blocs de la figure
				bloc = blocHibernateDAO.save(bloc);
				blocs.add(bloc);
			}
			
			// On met à jour la liste des blocs de la figure
			figure.setBlocs(blocs);
			figure = figureHibernateDAO.save(figure);
		}
		
		model.addAttribute(ATT_TETRIMINO, tetrimino);
		model.addAttribute(ATT_FIGURE, figure);
		
		//return "redirect:/modifFigure?idTetrimino=" + idTetrimino + "&idFigure=" + idFigure;
		return "modifFigure";
	}
	
	
	
	/**
	 * MODIFIER FIGURE (POST)
	 * @param idTetrimino
	 * @param idFigure
	 * @param f
	 * @param result
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modifFigure", method=RequestMethod.POST)
	public String modifFigure(@RequestParam(value="idTetrimino") int idTetrimino,
			@RequestParam(value="idFigure") int idFigure,
			@Valid @ModelAttribute("figure") Figure f,
			BindingResult result,
			Model model) {
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		Figure figure = figureHibernateDAO.find(idFigure);
		
		// S'il y a une erreur dans le formulaire
		if (result.hasErrors()) {
			model.addAttribute(ATT_TETRIMINO, tetrimino);
			model.addAttribute(ATT_FIGURE, figure);
			model.addAttribute(ATT_FIGURE_ID, idFigure);
			model.addAttribute(ATT_TETRIMINO_ID, idTetrimino);
			model.addAttribute(ATT_ERREUR, "Erreur dans la modification");
			
			//return "redirect:/modifFigure?idTetrimino=" + idTetrimino + "&idFigure=" + idFigure;
			return "modifFigure";
		}
		
		figure.setOrdreRotation(f.getOrdreRotation());
		figure = figureHibernateDAO.save(figure);
		
		return "redirect:/tetrimino?id=" + idTetrimino;
	}
	
	
	/**
	 * SUPPRIMER FIGURE (GET)
	 * @param idTetrimino
	 * @param idFigure
	 * @param session
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/supprimerFigure", method = RequestMethod.GET)
	public String supprimerFigure(@RequestParam(value="idTetrimino", required=true) int idTetrimino,
			@RequestParam(value="idFigure", required=true) int idFigure) {
		
		Figure figure = figureHibernateDAO.find(idFigure);
		
		// Suppression de la figure
		figureHibernateDAO.delete(figure);
		
		return "redirect:/tetrimino?id=" + idTetrimino;
		
	}
	
	
	
	@RequestMapping(value="/ordonnerFigure", method=RequestMethod.GET)
	public String ordonnerFigure(@RequestParam(value="idFigure", required=true) int idFigure,
			@RequestParam(value="sens", required=true) int sens) {

		Figure figure = figureHibernateDAO.find(idFigure);

		Tetrimino tetrimino = figure.getTetrimino();

		int nelOrdre;
		
		// Décrémentation de l'ordre
		if (sens == 0)
			nelOrdre = figure.getOrdreRotation() - 1;
		// Incrémentation de l'ordre
		else
			nelOrdre = figure.getOrdreRotation() + 1;
		
		// Gestion des cas où l'ordre deviendrait < à 0 ou > au nombre total de figure
		if (nelOrdre < 0 ) {
			nelOrdre = 0;
		} else if (nelOrdre > tetrimino.getFigures().size() - 1 ) {
			nelOrdre = tetrimino.getFigures().size() - 1 ;
		}

		
		for (Figure f : tetrimino.getFigures()) {
			if ( f.getOrdreRotation() == nelOrdre) {
				f.setOrdreRotation(figure.getOrdreRotation());

				figureHibernateDAO.save(f);
			}
		}
		
		figure.setOrdreRotation(nelOrdre);
		figureHibernateDAO.save(figure);
	
		return "redirect:/tetrimino?id=" + tetrimino.getId();
	}
	
	
	@ModelAttribute("figure")
	public Figure initFigure() {
		Figure figure = new Figure();
		return figure;
	}
}
