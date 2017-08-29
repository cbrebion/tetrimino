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

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;

@WebServlet("/listeParties")
public class PartiesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final String VUE_LISTE	= "/WEB-INF/listerParties.jsp";
	
	@Autowired
	private IPartieDAO partieHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		req.getSession().removeAttribute("blocs");
		
		List<Partie> parties = partieHibernateDAO.findAllWithScores();
		
		req.setAttribute("parties", parties);
		
		this.getServletContext().getRequestDispatcher(VUE_LISTE).forward(req, resp);
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
