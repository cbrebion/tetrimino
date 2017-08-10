package com.sopra.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IJoueurDAO;
import com.sopra.dao.ITetriminoDAO;
import com.sopra.dao.server.JoueurServerDAO;
import com.sopra.dao.server.TetriminoServerDAO;
import com.sopra.model.Joueur;
import com.sopra.model.Tetrimino;

@WebServlet("/listeJoueurs")
public class DisplayPlayerServlet extends HttpServlet {
	public static final String VUE_GET		= "/WEB-INF/AfficherJoueurs.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		IJoueurDAO joueurServerDAO = new JoueurServerDAO();

		List<Joueur> joueurs = joueurServerDAO.rechercher(req);

		req.setAttribute("tetriminos", joueurs);

		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

}
