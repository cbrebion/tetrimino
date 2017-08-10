package com.sopra.servlets;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.dao.server.TetriminoServerDAO;
import com.sopra.model.Tetrimino;

@WebServlet("/admin/listeTetriminos")
public class DisplayTetriminoServlet extends HttpServlet {
	public static final String VUE_GET		= "/WEB-INF/admin/AfficherTetriminos.jsp";
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		ITetriminoDAO tetriminoServerDAO = new TetriminoServerDAO();
		
		List<Tetrimino> tetriminos = tetriminoServerDAO.rechercher(req);
		
		req.setAttribute("tetriminos", tetriminos);
		
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
	}

}
