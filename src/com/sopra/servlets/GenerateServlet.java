package com.sopra.servlets;

import java.io.IOException;
import java.util.Random;

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
		
		adminHibernateDAO.save(admin);
		
		Admin admin1 = new Admin();
		admin.setNom("test1");
		admin.setPrenom("test1");
		admin.setPassword("test1");
		admin.setUsername("test1");
		
		adminHibernateDAO.save(admin);
		
		Joueur joueur1 = new Joueur();
		joueur1.setNom("joueur1");
		joueur1.setPrenom("joueur1");
		joueur1.setPassword("joueur1");
		joueur1.setUsername("joueur1");
		
		joueurHibernateDAO.save(joueur1);
		
		Joueur joueur2 = new Joueur();
		joueur2.setNom("joueur2");
		joueur2.setPrenom("joueur2");
		joueur2.setPassword("joueur2");
		joueur2.setUsername("joueur2");
		
		joueurHibernateDAO.save(joueur2);
		
		Tetrimino tetrimino = new Tetrimino();
		tetrimino.setNom("tetrimino");
		tetrimino.setId(1);
		tetrimino.setCouleur("tetrimino");
		
		tetriminoHibernateDAO.save(tetrimino);
		
		Partie partie = new Partie();
		partie.setFinie(true);
		partie.setId(1);
		partie.setJoueur1(joueur1);
		partie.setJoueur2(joueur2);
		partie.setScore((int) Math.random());
		
		
		
	}

}
