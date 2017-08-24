package com.sopra.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;


@Stateless
public class PartieHibernateDAO implements IPartieDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Partie> findAll() {
		try {
			return (List<Partie>)em.createQuery("FROM Partie").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}
	
	@Override
	public List<Partie> findAllWithScores() {
		return (List<Partie>) em.createQuery("select distinct p from Partie p left join fetch p.scores").getResultList();
	}

	@Override
	public Partie find(int id) {
		try {
			return em.find(Partie.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public List<Partie> findAllDesc() {
		try {
			return (List<Partie>) em.createQuery("FROM Partie p ORDER BY p.score DESC").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Partie save(Partie partie) {
		return em.merge(partie);
	}

	@Override
	public void delete(Partie partie) {
		em.remove(em.merge(partie));
	}

}
