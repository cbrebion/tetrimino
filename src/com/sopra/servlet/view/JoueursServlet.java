package com.sopra.servlet.view;

import java.io.IOException;
import java.util.List;

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

@WebServlet("/listeJoueurs")
public class JoueursServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public static final String VUE_GET		= "/WEB-INF/afficherJoueurs.jsp";
	
	@Autowired
	private IJoueurDAO joueurHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("blocs");
		
		List<Joueur> joueurs = joueurHibernateDAO.findAll();

		req.setAttribute("joueurs", joueurs);

		this.getServletContext().getRequestDispatcher(VUE_GET).forward(req, resp);
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