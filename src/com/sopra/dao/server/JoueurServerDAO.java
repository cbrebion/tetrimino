package com.sopra.dao.server;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;
import com.sopra.model.Tetrimino;

public class JoueurServerDAO implements IJoueurDAO{
	public static final String ATT_LIST_JOUEURS	= "joueurs";
	public static final String ATT_JOUEUR	   	= "joueur";
	

	public List<Joueur> findAll(HttpServletRequest req) {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		
		joueurs = (List<Joueur>) req.getServletContext().getAttribute(ATT_LIST_JOUEURS);
		
		return joueurs;
	}

	public Joueur find(HttpServletRequest req, int id) {
		List<Joueur> joueurs = findAll(req);
		Joueur joueur = null;
		
		for (Joueur joue : joueurs) {
			if (joue.getId() == id) {
				joueur = joue;
			}
		}
		
		return joueur;
	}

	public Joueur modifier(HttpServletRequest req, Joueur newJoueur) {
		List<Joueur> joueurs = new ArrayList<Joueur>();
		Joueur joueur;
		
		joueurs = findAll(req);
		joueur = find(req, newJoueur.getId());
		
		joueur.setUsername(newJoueur.getUsername());
		joueur.setNom(newJoueur.getNom());
		joueur.setPrenom(newJoueur.getPrenom());
		
		return joueur;
	}

	public void delete(HttpServletRequest req, int id) {
		List<Joueur> joueurs = findAll(req);
		Joueur joueur = null;
		
		for (Joueur joue : joueurs) {
			if (joue.getId() == id) {
				joueur = joue;
			}
		}
		joueurs.remove(joueur);
	}
	
	public void save(HttpServletRequest req, Joueur joue) {
		List<Joueur> joueurs = findAll(req);
		
		joueurs.add(joue);
		
		req.getServletContext().setAttribute(ATT_LIST_JOUEURS, joueurs);
	}

	@Override
	public List<Joueur> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joueur find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Joueur save(Joueur obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Joueur obj) {
		// TODO Auto-generated method stub
		
	}

}