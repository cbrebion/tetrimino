package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IBlocDAO;
import com.sopra.model.Bloc;

@Repository
@Transactional
public class BlocHibernateDAO implements IBlocDAO {
	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Bloc> findAll() {
		try {
			return (List<Bloc>)em.createQuery("FROM Bloc").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Bloc find(int id) {
		try {
			return em.find(Bloc.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Bloc save(Bloc bloc) {
		return em.merge(bloc);
	}

	@Override
	public void delete(Bloc bloc) {
		em.remove(em.merge(bloc));
	}

}
