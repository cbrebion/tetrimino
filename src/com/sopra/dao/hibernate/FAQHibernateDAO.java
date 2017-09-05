package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IFAQDAO;
import com.sopra.model.FAQ;

@Repository
@Transactional
public class FAQHibernateDAO implements IFAQDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<FAQ> findAll() {
		try {
			return (List<FAQ>)em.createQuery("FROM FAQ").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public FAQ find(int id) {
		try {
			return em.find(FAQ.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public FAQ save(FAQ faq) {
		return em.merge(faq);
	}

	@Override
	public void delete(FAQ faq) {
		em.remove(em.merge(faq));
	}

}
