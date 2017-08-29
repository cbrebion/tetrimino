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

import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Tetrimino;

@WebServlet("/supprimerPiece")
public class TetriminoDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String ATT_ID				= "id";
	private static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		int id = Integer.parseInt(req.getParameter(ATT_ID));
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		tetriminoHibernateDAO.delete(tetrimino);
		
		resp.sendRedirect(VUE_POST);
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
