package com.sopra.servlet.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import com.sopra.dao.IBlocDAO;
import com.sopra.dao.IFigureDAO;
import com.sopra.dao.ITetriminoDAO;
import com.sopra.model.Bloc;
import com.sopra.model.Figure;
import com.sopra.model.Tetrimino;

/**
 * Servlet implementation class FigureAddServlet
 */
@WebServlet("/admin/ajoutFigure")
public class FigureAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_AJOUT_FIGURE	= "/WEB-INF/admin/ajouterFigure.jsp";
	private static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	private static final String PARAM_ID			= "id";
	private static final String PARAM_X				= "x";
	private static final String PARAM_Y				= "y";
	private static final String PARAM_ORDRE			= "ordre";
	private static final String ATT_TETRI			= "tetrimino";
	private static final String ATT_BLOCS			= "blocs";
	
	private List<Bloc> blocs						= new ArrayList<Bloc>();
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@Autowired
	private IBlocDAO blocHibernateDAO;
	
	@Autowired
	private IFigureDAO figureHibernateDAO;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		if (request.getAttribute(ATT_BLOCS) != null)
			blocs = (List<Bloc>) request.getAttribute(ATT_BLOCS);
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		if (request.getParameter(PARAM_X) != null) {
			int x = Integer.parseInt(request.getParameter(PARAM_X));
			int y = Integer.parseInt(request.getParameter(PARAM_Y));
			
			Bloc bloc = new Bloc();
			bloc.setX(x);
			bloc.setY(y);
			
//			bloc = blocHibernateDAO.save(bloc); // A DECOMMENTER !!!!
			
			blocs.add(bloc);
		}
		
		request.setAttribute(ATT_BLOCS, blocs);
		request.setAttribute(ATT_TETRI, tetrimino);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_FIGURE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if (request.getAttribute(ATT_BLOCS) != null)
			blocs = (List<Bloc>) request.getAttribute(ATT_BLOCS);
		
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		int ordre = Integer.parseInt(request.getParameter(PARAM_ORDRE));
		
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		Figure figure = new Figure();
		figure.setTetrimino(tetrimino);
		figure.setOrdreRotation(ordre);
		figure.setBlocs(blocs);
		
		figure = figureHibernateDAO.save(figure);
		
		for (Bloc bloc : blocs) {
			bloc.setFigure(figure);
			bloc = blocHibernateDAO.save(bloc);
		}
		
		blocs.clear();
		
		response.sendRedirect(VUE_POST);
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
