package com.sopra.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IAdminDAO;
import com.sopra.dao.IJoueurDAO;
import com.sopra.dao.IPartieDAO;
import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Admin;
import com.sopra.model.Joueur;
import com.sopra.model.Partie;
import com.sopra.model.Tetrimino;


@WebServlet("/")
public class GenerateServlet extends HttpServlet {

	@EJB(name="adminHibernateDAO")
	private IAdminDAO adminHibernateDAO;
	
	
	@EJB(name="joueurHibernateDAO")
	private IJoueurDAO joueurHibernateDAO;
	
	@EJB(name="tetriminoHibernateDAO")
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@EJB(name="partieHibernateDAO")
	private IPartieDAO partieHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		Admin admin = new Admin();
		admin.setNom("test");
		admin.setPrenom("test");
		admin.setPassword("test");
		admin.setUsername("test");
		
		admin = adminHibernateDAO.save(admin);
		
		Admin admin1 = new Admin();
		admin1.setNom("test1");
		admin1.setPrenom("test1");
		admin1.setPassword("test1");
		admin1.setUsername("test1");
		
		admin1 = adminHibernateDAO.save(admin1);
		
		Joueur joueur1 = new Joueur();
		joueur1.setNom("joueur1");
		joueur1.setPrenom("joueur1");
		joueur1.setPassword("joueur1");
		joueur1.setUsername("joueur1");
		
		joueur1 = joueurHibernateDAO.save(joueur1);
		
		Joueur joueur2 = new Joueur();
		joueur2.setNom("joueur2");
		joueur2.setPrenom("joueur2");
		joueur2.setPassword("joueur2");
		joueur2.setUsername("joueur2");
		
		joueur2 = joueurHibernateDAO.save(joueur2);
		
		Tetrimino tetrimino = new Tetrimino();
		tetrimino.setNom("tetrimino");
		tetrimino.setCouleur("tetrimino");
		
		tetrimino = tetriminoHibernateDAO.save(tetrimino);
		
		Partie partie = new Partie();
		partie.setFinie(true);
		partie.setJoueur1(joueur1);
		partie.setJoueur2(joueur2);
		partie.setScore(210);
		
		partie = partieHibernateDAO.save(partie);
		
	}

}
