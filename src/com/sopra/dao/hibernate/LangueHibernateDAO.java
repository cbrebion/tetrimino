package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.ILangueDAO;
import com.sopra.model.Langue;

@Repository
@Transactional
public class LangueHibernateDAO implements ILangueDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<Langue> findAll() {
		try {
			return (List<Langue>)em.createQuery("FROM Langue").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Langue find(int id) {
		try {
			return em.find(Langue.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Langue save(Langue langue) {
		return em.merge(langue);
	}

	@Override
	public void delete(Langue faq) {
		em.remove(em.merge(faq));
	}

}
