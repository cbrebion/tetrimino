package com.sopra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.dao.server.TetriminoServerDAO;

@WebServlet("/supprimerPiece")
public class DeleteTetriminoServlet extends HttpServlet {
	private static final String ATT_ID				= "id";
	private static final String VUE_POST			= "/tetrimino/listeTetriminos";

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		ITetriminoDAO tetriminoServerDAO = new TetriminoServerDAO();
		int id = Integer.parseInt(req.getParameter(ATT_ID));
		tetriminoServerDAO.supprimer(req, id);
		
		resp.sendRedirect(VUE_POST);
	}

}
