package com.sopra.dao.hibernate;
import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IJoueurDAO;
import com.sopra.model.Joueur;


@Stateless
public class JoueurHibernateDAO implements IJoueurDAO {

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Joueur> findAll() {
		try {
			return (List<Joueur>) em.createQuery("FROM Joueur").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Joueur find(int id) {
		try {
			return em.find(Joueur.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Joueur save(Joueur joueur) {
		return em.merge(joueur);
	}

	@Override
	public void delete(Joueur joueur) {
		em.remove(joueur);

	}

}
