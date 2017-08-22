package com.sopra.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IPartieDAO;
import com.sopra.model.Partie;


@Stateless
public class PartieHibernateDAO implements IPartieDAO {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Partie> findAll() {
	return (List<Partie>)em.createQuery("FROM Partie").getResultList();

	}

	@Override
	public Partie find(int id) {
	return em.find(Partie.class, id);
	}

	@Override
	public Partie save(Partie partie) {
	return em.merge(partie);
	}

	@Override
	public void delete(Partie partie) {
	em.remove(partie);

	}

}
