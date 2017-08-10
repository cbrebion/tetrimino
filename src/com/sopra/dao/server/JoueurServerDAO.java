package com.sopra.dao.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;
import com.sopra.model.Tetrimino;

public class JoueurServerDAO implements IJoueurDAO{
	public static final String ATT_LIST_JOUEURS	= "joueurs";

	@Override
	public List<Joueur> rechercher(HttpServletRequest req) {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		
		joueurs = (List<Joueur>) req.getServletContext().getAttribute(ATT_LIST_JOUEURS);
		
		return joueurs;
	}

	@Override
	public Joueur rechercher(HttpServletRequest req, int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joueur modifier(Joueur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void supprimer(HttpServletRequest req, int id) {
		// TODO Auto-generated method stub
		
	}

}