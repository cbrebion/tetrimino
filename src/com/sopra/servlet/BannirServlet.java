package com.sopra.servlet;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;

@WebServlet("/admin/bannir")
public class BannirServlet extends HttpServlet {
	
	private static final String VUE_LISTE_JOUEUR	= "/tetrimino/listeJoueurs";

	@EJB(name="joueurHibernateDAO")
	private IJoueurDAO joueurHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Joueur joueur = joueurHibernateDAO.find(id);
		
		joueur.setBanni(!joueur.getBanni());
		
		joueur = joueurHibernateDAO.save(joueur);
		
		resp.sendRedirect(VUE_LISTE_JOUEUR);
		
		
	}
	
}
