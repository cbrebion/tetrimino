package com.sopra.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
	private static final String VUE_GET		= "/WEB-INF/Accueil.jsp";
	private static final String VUE_POST	= "accueilAdmin";
	
	private static final String CHAMP_USER	= "username";
	private static final String CHAMP_PASS	= "pass";
	
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
		
		if (username.equals("admin") && pass.equals("admin")) {
			req.getSession().setAttribute("username", username);
			req.getSession().setAttribute("pass", pass);
		}
		this.doGet(req, resp);
	}
}
