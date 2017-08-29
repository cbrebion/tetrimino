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
@WebServlet("/ajoutFigure")
public class FigureAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static final String VUE_AJOUT_FIGURE	= "/WEB-INF/ajouterFigure.jsp";
	private static final String VUE_POST			= "/tetrimino/listeTetriminos";
	
	private static final String PARAM_ID			= "id";
	private static final String PARAM_X				= "x";
	private static final String PARAM_Y				= "y";
	private static final String CHAMP_ORDRE			= "ordre";
	private static final String ATT_TETRI			= "tetrimino";
	private static final String SESSION_BLOCS		= "blocs";
	
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
	@SuppressWarnings("unchecked")
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Récupération de l'id du tetrimino
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		// Récupération de la liste des blocs déjà sélectionné
		if (request.getSession().getAttribute(SESSION_BLOCS) != null)
			blocs = (List<Bloc>) request.getSession().getAttribute(SESSION_BLOCS);
		else
			blocs = new ArrayList<Bloc>();
		
		// Récupération du tetrimino
		Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
		
		// S'il y a bien un paramètre x dans la reqûete
		if (request.getParameter(PARAM_X) != null) {
			int x = Integer.parseInt(request.getParameter(PARAM_X));
			int y = Integer.parseInt(request.getParameter(PARAM_Y));
			
			// Assignation des valeurs au bloc
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
		
		// Envoi de la liste des blocs en cours et du tetrimino
		request.getSession().setAttribute(SESSION_BLOCS, blocs);
		request.setAttribute(ATT_TETRI, tetrimino);
		
		this.getServletContext().getRequestDispatcher(VUE_AJOUT_FIGURE).forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@SuppressWarnings("unchecked")
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Réinitialisation des erreurs
		erreurs.clear();
		
		// Récupération de la liste des blocs
		if (request.getSession().getAttribute(SESSION_BLOCS) != null) {
			blocs = (List<Bloc>) request.getSession().getAttribute(SESSION_BLOCS);
		}
		
		// Si on tente de valider sans avoir cliqué sur un seul bloc
		if (blocs.size() == 0) {
			setErreurs("bloc", "Veuillez créer au moins une figure");
		}
		
		
		// Récupération de l'id du tetrimino
		int id = Integer.parseInt(request.getParameter(PARAM_ID));
		
		// Récupération de l'ordre de rotation (avec validation)
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
		
		// Si tout roule
		if (erreurs.isEmpty()) {
		
			// Récupération du tetrimino
			Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
			
			// Création et initialisation de la figure
			Figure figure = new Figure();
			figure.setTetrimino(tetrimino);
			figure.setOrdreRotation(ordre);
			figure.setBlocs(blocs);
			
			figure = figureHibernateDAO.save(figure);
			
			// Affectation de chaque bloc à la figure + enregistrement des blocs
			for (Bloc bloc : blocs) {
				bloc.setFigure(figure);
				bloc = blocHibernateDAO.save(bloc);
			}
			
			// Reset de la liste de blocs
			blocs.clear();
			request.getSession().removeAttribute(SESSION_BLOCS);
		
			response.sendRedirect(VUE_POST);
		}
		// Si une erreur est survenue au POST
		else {
			Tetrimino tetrimino = tetriminoHibernateDAO.find(id);
			
			// Envoi des erreurs et des variables utiles
			request.setAttribute("erreurs", erreurs);
			request.getSession().setAttribute(SESSION_BLOCS, blocs);
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
