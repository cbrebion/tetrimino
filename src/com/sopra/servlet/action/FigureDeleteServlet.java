package com.sopra.servlet.action;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sopra.dao.IFigureDAO;
import com.sopra.model.Figure;

/**
 * Servlet implementation class FigureDeleteServlet
 */
//@WebServlet("/supprimerFigure")
public class FigureDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_ID_TETRIMINO	= "idTetrimino";
	private static final String ATT_ID_FIGURE		= "idFigure";
	private static final String VUE_POST			= "/tetrimino/tetrimino";
	
	@Autowired
	private IFigureDAO figureHibernateDAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des id de tetrimino et figure
		int idTetrimino = Integer.parseInt(request.getParameter(ATT_ID_TETRIMINO));
		int idFigure = Integer.parseInt(request.getParameter(ATT_ID_FIGURE));
		
		Figure figure = figureHibernateDAO.find(idFigure);
		
		// Suppression de la figure
		figureHibernateDAO.delete(figure);
		
		// Redirection vers la vue du tetrimino
		response.sendRedirect(VUE_POST + "?id=" + idTetrimino);
	}

	@Override
	public void init(ServletConfig config) throws ServletException {
		try {
			super.init(config);
		} catch (ServletException e) {
			e.printStackTrace();
		}
		
		SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this, config.getServletContext());
	}

}
