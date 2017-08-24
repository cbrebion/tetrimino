package com.sopra.servlets;

import java.io.IOException;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;

@WebServlet("/accueilJoueur")
public class HomeJoueurServlet extends HttpServlet {
	private static final String VUE_ACCUEIL		= "/WEB-INF/accueilJoueur.jsp";

	@EJB(name="partieHibernateDAO")
	private IPartieDAO partieHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		List<Partie> parties = partieHibernateDAO.findAllDesc();
		
		req.setAttribute("parties", parties);

		this.getServletContext().getRequestDispatcher(VUE_ACCUEIL).forward(req, resp);
	}

}
