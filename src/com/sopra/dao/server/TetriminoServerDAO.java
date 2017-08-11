package com.sopra.dao.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

public class TetriminoServerDAO implements ITetriminoDAO {
	public static final String ATT_LIST_TETRIMINOS		= "tetriminos";
	public static final String ATT_TETRIMINO			= "tetrimino";

	@Override
	public List<Tetrimino> rechercher(HttpServletRequest req) {
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		
		tetriminos = (List<Tetrimino>) req.getServletContext().getAttribute(ATT_LIST_TETRIMINOS);
		
		return tetriminos;
	}

	@Override
	public Tetrimino rechercher(HttpServletRequest req, int id) {
		List<Tetrimino> tetriminos = rechercher(req);
		Tetrimino tetrimino = null;
		
		for (Tetrimino tetri : tetriminos) {
			if (tetri.getId() == id) {
				tetrimino = tetri;
			}
		}
		
		return tetrimino;
	}

	@Override
	public Tetrimino modifier(HttpServletRequest req, Tetrimino newTetrimino) {		
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		Tetrimino tetrimino;
		
		tetriminos = rechercher(req);
		tetrimino = rechercher(req, newTetrimino.getId());
		
		tetrimino.setNom(newTetrimino.getNom());
		tetrimino.setCouleur(newTetrimino.getCouleur());
		
		return tetrimino;
	}

	@Override
	public void supprimer(HttpServletRequest req, int id) {
		List<Tetrimino> tetriminos = rechercher(req);
		Tetrimino tetrimino = null;
		
		for (Tetrimino tetri : tetriminos) {
			if (tetri.getId() == id) {
				tetrimino = tetri;
			}
		}
		tetriminos.remove(tetrimino);
	}

	@Override
	public void enregistrer(HttpServletRequest req, Tetrimino tetri) {
		List<Tetrimino> tetriminos = rechercher(req);
		
		tetriminos.add(tetri);
		
		req.getServletContext().setAttribute(ATT_LIST_TETRIMINOS, tetriminos);
	}

}
