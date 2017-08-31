package com.sopra.servlet.view;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

/**
 * Servlet implementation class TetriminoServlet
 */
//@WebServlet("/tetrimino")
public class TetriminoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_GET		= "/WEB-INF/views/jsp/afficherTetrimino.jsp";
	
	private static final String PARAM_ID	= "id";
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		request.setAttribute("tetrimino", tetrimino);
		
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(request, response);
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
