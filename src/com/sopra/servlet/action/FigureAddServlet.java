package com.sopra.servlet.action;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.sopra.exception.FormValidationException;
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
	
	private Map<String, String> erreurs				= new HashMap<String, String>();
	
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
			
			// Vérifie si le bloc n'a pas déjà été sélectionné
			int i = 0;
			int indexExiste = -1;
			for (Bloc blocCurrent : blocs) {
				if ((blocCurrent.getX() == bloc.getX()) && (blocCurrent.getY() == bloc.getY())) {
					indexExiste = i;
				}
				i++;
			}

			// Retire le bloc de la liste s'il a été désélectionné
			if (indexExiste != -1) {
				blocs.remove(blocs.get(indexExiste));
			}
			// Le rajoute sinon
			else {
				blocs.add(bloc);
			}
		}
		
		request.setAttribute(ATT_BLOCS, blocs);
		request.setAttribute(ATT_TETRI, tetrimino);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_FIGURE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreurs.clear();
		
		if (request.getAttribute(ATT_BLOCS) != null)
			blocs = (List<Bloc>) request.getAttribute(ATT_BLOCS);
		
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		int ordre;
		try {
			ordre = Integer.parseInt(request.getParameter(PARAM_ORDRE));
			validationOrdre(ordre);
		} catch (NumberFormatException nfe) {
			setErreurs("ordre", "Merci de rentrer un ordre");
			ordre = -1;
		} catch (FormValidationException fve) {
			setErreurs("ordre", fve.getMessage());
			ordre = -1;
		}
		
		if (erreurs.isEmpty()) {
		
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
		else {
			Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
			
			request.setAttribute("erreurs", erreurs);
			request.setAttribute(ATT_BLOCS, blocs);
			request.setAttribute(ATT_TETRI, tetrimino);
			
			this.getServletContext().getRequestDispatcher(VUE_AJOUT_FIGURE).forward(request, response);
		}
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
	
	private void validationOrdre(int ordre) throws FormValidationException {
		if (ordre < 0) {
			throw new FormValidationException("L'ordre doit être supérieur ou égal à 0");
		}
	}
	
	public Map<String, String> getErreurs() {
		return erreurs;
	}
	
	public void setErreurs(String champ, String message) {
		erreurs.put(champ, message);
	}

}
