package com.sopra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/accueilJoueur")
public class HomeJoueurServlet extends HttpServlet {
	private static final String VUE_ACCUEIL		= "/WEB-INF/accueilJoueur.jsp";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
	}

}
