package com.sopra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/modifPiece")
public class ModifyTetriminoServlet extends HttpServlet {
	private static final String VUE_GET		= "/WEB-INF/modifierTetrimino.jsp";
	
	private static final String PARAM_ID	= "id";
	
	private static final String ATT_NOM		= "nom";
	private static final String ATT_COULEUR	= "couleur";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter(PARAM_ID));
		
		this.getServletContext().getRequestDispatcher(VUE_GET + "?id=" + id).forward(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String nom = req.getParameter(ATT_NOM);
		String couleur = req.getParameter(ATT_COULEUR);
	}
}
