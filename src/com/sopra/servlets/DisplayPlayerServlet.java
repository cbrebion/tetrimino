package com.sopra.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IJoueurDAO;
import com.sopra.dao.server.JoueurServerDAO;
import com.sopra.model.Joueur;

@WebServlet("/listeJoueurs")
public class DisplayPlayerServlet extends HttpServlet {
	public static final String VUE_GET		= "/WEB-INF/AfficherJoueurs.jsp";
	
	@EJB(name="joueurHibernateDAO")
	private IJoueurDAO joueurHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		List<Joueur> joueurs = joueurHibernateDAO.findAll();

		req.setAttribute("joueurs", joueurs);

		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

}