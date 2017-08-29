package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IFigureDAO;
import com.sopra.model.Figure;

@Repository
@Transactional
public class FigureHibernateDAO implements IFigureDAO {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Figure> findAll() {
		try {
			return (List<Figure>)em.createQuery("FROM Figure f ORDER BY f.ordreRotation").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Figure find(int id) {
		try {
			return em.find(Figure.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Figure save(Figure figure) {
		return em.merge(figure);
	}

	@Override
	public void delete(Figure figure) {
		em.remove(em.merge(figure));
	}

}
