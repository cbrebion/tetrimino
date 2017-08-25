package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IPersonneDAO;
import com.sopra.model.Personne;


@Repository
@Transactional
public class PersonneHibernateDAO implements IPersonneDAO {
	
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Personne> findAll() {
		try {
			return (List<Personne>)em.createQuery("FROM Personne").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Personne find(int id) {
		try {
			return em.find(Personne.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}
	
	public Personne findByUsername(String username) {
		try {
			return (Personne) em.createQuery("FROM Personne p WHERE p.username = '" + username + "'").getSingleResult();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Personne save(Personne personne) {
		return em.merge(personne);
	}

	@Override
	public void delete(Personne personne) {
		em.remove(em.merge(personne));
	}

}
