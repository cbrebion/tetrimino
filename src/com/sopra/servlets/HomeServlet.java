package com.sopra.servlets;

import java.io.IOException;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IAdminDAO;
import com.sopra.dao.IJoueurDAO;
import com.sopra.dao.IPersonneDAO;

@WebServlet("/accueil")
public class HomeServlet extends HttpServlet {
	private static final String VUE_GET		= "/WEB-INF/Accueil.jsp";
	private static final String VUE_POST	= "accueilAdmin";
	
	private static final String CHAMP_USER	= "username";
	private static final String CHAMP_PASS	= "password";
	
	@EJB(name="personneHibernateDAO")
	private IPersonneDAO personneHibernateDAO;
	
	@EJB(name="adminHibernateDAO")
	private IAdminDAO adminHibernateDAO;
	
	@EJB(name="joueurHibernateDAO")
	private IJoueurDAO joueurHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		if (req.getSession().getAttribute(CHAMP_USER) == null) {
			this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
		} else {
			resp.sendRedirect(VUE_POST);
		}
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String username = req.getParameter(CHAMP_USER);
		String pass = req.getParameter(CHAMP_PASS);
		
		Personne personne = personneHibernateDAO.find(id);
		
		req.getSession().setAttribute("username", personne.getUsername());
		
		req.setAttribute("valUsername", username);
		
		this.doGet(req, resp);
	}
}
