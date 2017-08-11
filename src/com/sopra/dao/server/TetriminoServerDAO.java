package com.sopra.dao.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

public class TetriminoServerDAO implements ITetriminoDAO {
	public static final String ATT_LIST_TETRIMINOS		= "tetriminos";

	@Override
	public List<Tetrimino> rechercher(HttpServletRequest req) {
		List<Tetrimino> tetriminos = new ArrayList<Tetrimino>();
		
		tetriminos = (List<Tetrimino>) req.getServletContext().getAttribute(ATT_LIST_TETRIMINOS);
		
		return tetriminos;
	}

	@Override
	public Tetrimino rechercher(HttpServletRequest req, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tetrimino modifier(Tetrimino obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(HttpServletRequest req, int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void enregistrer(HttpServletRequest req, Tetrimino tetri) {
		List<Tetrimino> tetriminos = rechercher(req);
		
		tetriminos.add(tetri);
		
		req.getServletContext().setAttribute(ATT_LIST_TETRIMINOS, tetriminos);
	}

}
