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

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;

@WebServlet("/admin/bannir")
public class BannirServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VUE_LISTE_JOUEUR	= "/tetrimino/listeJoueurs";

	@Autowired
	private IJoueurDAO joueurHibernateDAO;
	
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		
		Joueur joueur = joueurHibernateDAO.find(id);
		
		joueur.setBanni(!joueur.getBanni());
		
		joueur = joueurHibernateDAO.save(joueur);
		
		resp.sendRedirect(VUE_LISTE_JOUEUR);
		
		
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
