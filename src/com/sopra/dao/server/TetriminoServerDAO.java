package com.sopra.dao.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

public class TetriminoServerDAO implements ITetriminoDAO {
	public static final String ATT_LIST_TETRIMINOS		= "tetriminos";
	public static final String ATT_TETRIMINO			= "tetrimino";


	public List<Tetrimino> findAll(HttpServletRequest req) {
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		
		tetriminos = (List<Tetrimino>) req.getServletContext().getAttribute(ATT_LIST_TETRIMINOS);
		
		return tetriminos;
	}

	public Tetrimino find(HttpServletRequest req, int id) {
		List<Tetrimino> tetriminos = findAll(req);
		Tetrimino tetrimino = null;
		
		for (Tetrimino tetri : tetriminos) {
			if (tetri.getId() == id) {
				tetrimino = tetri;
			}
		}
		
		return tetrimino;
	}

	public Tetrimino modifier(HttpServletRequest req, Tetrimino newTetrimino) {		
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		Tetrimino tetrimino;
		
		tetriminos = findAll(req);
		tetrimino = find(req, newTetrimino.getId());
		
		tetrimino.setNom(newTetrimino.getNom());
		tetrimino.setCouleur(newTetrimino.getCouleur());
		
		return tetrimino;
	}

	public void delete(HttpServletRequest req, int id) {
		List<Tetrimino> tetriminos = findAll(req);
		Tetrimino tetrimino = null;
		
		for (Tetrimino tetri : tetriminos) {
			if (tetri.getId() == id) {
				tetrimino = tetri;
			}
		}
		tetriminos.remove(tetrimino);
	}

	public void save(HttpServletRequest req, Tetrimino tetri) {
		List<Tetrimino> tetriminos = findAll(req);
		
		tetriminos.add(tetri);
		
		req.getServletContext().setAttribute(ATT_LIST_TETRIMINOS, tetriminos);
	}

}
