package com.sopra.servlet.action;

import java.io.IOException;
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
 * Servlet implementation class FigureModifyServlet
 */
@WebServlet("/modifFigure")
public class FigureModifyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Autowired
	private ITetriminoDAO tetriminoHibernateDAO;
	
	@Autowired
	private IFigureDAO figureHibernateDAO;
	
	@Autowired
	private IBlocDAO blocHibernateDAO;
	
	public static final String VUE_GET				= "/WEB-INF/modifierFigure.jsp";
	public static final String VUE_POST				= "/tetrimino/tetrimino";
	
	private static final String PARAM_ID_TETRIMINO	= "idTetrimino";
	private static final String PARAM_ID_FIGURE		= "idFigure";
	private static final String PARAM_X				= "x";
	private static final String PARAM_Y				= "y";
	private static final String CHAMP_ORDRE			= "ordre";
	
	private static final String CHAMP_ID_FIGURE		= "idFigure";
	private static final String CHAMP_ID_TETRIMINO	= "idTetrimino";
	
	private static final String ATT_TETRI			= "tetrimino";
	private static final String ATT_FIGURE			= "figure";
	private static final String ATT_ERREUR			= "erreurs";
	
	private Map<String, String> erreurs		= new HashMap<String, String>();

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération des id tetrimino et figure
		int idTetrimino = Integer.parseInt(request.getParameter(PARAM_ID_TETRIMINO));
		int idFigure = Integer.parseInt(request.getParameter(PARAM_ID_FIGURE));
		
		Bloc blocToDelete;
		
		// Récupération du tetrimino et de la figure à modifier
		Tetrimino tetrimino = tetriminoHibernateDAO.find(idTetrimino);
		Figure figure = figureHibernateDAO.find(idFigure);
		
		if (request.getParameter(PARAM_X) != null) {
			int x = Integer.parseInt(request.getParameter(PARAM_X));
			int y = Integer.parseInt(request.getParameter(PARAM_Y));
			
			Bloc bloc = new Bloc();
			bloc.setX(x);
			bloc.setY(y);
			bloc.setFigure(figure);
			
			// Récupération de la liste des blocs liés à la figure
			List<Bloc> blocs = figure.getBlocs();
			
			// Vérifie si le bloc n'a pas déjà été sélectionné
			int i = 0;
			int indexExiste = -1;
			for (Bloc blocCurrent : blocs) {
				// Désélection
				if ((blocCurrent.getX() == bloc.getX()) && (blocCurrent.getY() == bloc.getY())) {
					indexExiste = i;
					// Supprime le bloc de la DB
					blocToDelete = blocHibernateDAO.find(blocCurrent.getId());
					blocHibernateDAO.delete(blocToDelete);
				}
				i++;
			}

			// Si le bloc existe déjà (désélection)
			if (indexExiste != -1) {
				// Suppression du bloc présent dans la liste de blocs de la figure
				blocs.remove(blocs.get(indexExiste));
			}
			// Sinon on le rajoute (sélection)
			else {
				// Ajout en DB + dans la liste des blocs de la figure
				bloc = blocHibernateDAO.save(bloc);
				blocs.add(bloc);
			}
			
			// On met à jour la liste des blocs de la figure
			figure.setBlocs(blocs);
			figure = figureHibernateDAO.save(figure);
		}
		
		request.setAttribute(ATT_TETRI, tetrimino);
		request.setAttribute(ATT_FIGURE, figure);
		
		//this.getServletContext().getRequestDispatcher(VUE_GET + "?id=" + id).forward(req, resp);
		this.getServletContext().getRequestDispatcher(VUE_GET).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		erreurs.clear();
		
		int idFigure = Integer.parseInt(request.getParameter(CHAMP_ID_FIGURE));
		int idTetrimino = Integer.parseInt(request.getParameter(CHAMP_ID_TETRIMINO));
		
		Figure figure = figureHibernateDAO.find(idFigure);
		
		// Récupération et validation de l'ordre de rotation
		int ordre;
		try {
			ordre = Integer.parseInt(request.getParameter(CHAMP_ORDRE));
			validationOrdre(ordre);
		} catch (NumberFormatException nfe) {
			setErreurs("ordre", "Merci de rentrer un ordre");
			ordre = -1;
		} catch (FormValidationException fve) {
			setErreurs("ordre", fve.getMessage());
			ordre = -1;
		}
		
		// Si tout est correct
		if (erreurs.isEmpty()) {
			figure.setOrdreRotation(ordre);
			figure = figureHibernateDAO.save(figure);
			
			response.sendRedirect(VUE_POST + "?id=" + idTetrimino);
		}
		// Sinon
		else {
			request.setAttribute(ATT_ERREUR, erreurs);
			request.setAttribute(ATT_TETRI, idTetrimino);
			request.setAttribute(ATT_FIGURE, figure);
			
			this.getServletContext().getRequestDispatcher(VUE_GET).forward(request, response);
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
