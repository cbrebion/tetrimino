package com.sopra.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IPersonneDAO;
import com.sopra.model.Personne;


@Stateless
public class PersonneHibernateDAO implements IPersonneDAO {
	
	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Personne> findAll() {
		return (List<Personne>)em.createQuery("FROM Personne").getResultList();
	}

	@Override
	public Personne find(int id) {
		return em.find(Personne.class, id);
	}

	@Override
	public Personne save(Personne personne) {
		return em.merge(personne);
	}

	@Override
	public void delete(Personne personne) {
		em.remove(personne);

	}

}
