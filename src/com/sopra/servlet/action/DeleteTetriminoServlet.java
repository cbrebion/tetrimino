package com.sopra.servlet.action;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

@WebServlet("/admin/supprimerPiece")
public class DeleteTetriminoServlet extends HttpServlet {
	private static final String ATT_ID				= "id";
	private static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	@EJB(name="tetriminoHibernateDAO")
	private ITetriminoDAO tetriminoHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter(ATT_ID));
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		tetriminoHibernateDAO.delete(tetrimino);
		
		resp.sendRedirect(VUE_POST);
	}

}
