package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IFAQLangueDAO;
import com.sopra.model.FAQLangue;

@Repository
@Transactional
public class FAQLangueHibernateDAO implements IFAQLangueDAO {
	
	@PersistenceContext
	private EntityManager em;

	@SuppressWarnings("unchecked")
	@Override
	public List<FAQLangue> findAll() {
		try {
			return (List<FAQLangue>)em.createQuery("FROM FAQLangue").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public FAQLangue find(int id) {
		try {
			return em.find(FAQLangue.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public FAQLangue save(FAQLangue faqLangue) {
		return em.merge(faqLangue);
	}

	@Override
	public void delete(FAQLangue faqLangue) {
		em.remove(em.merge(faqLangue));
	}

	@Override
	public List<FAQLangue> findAllLanguage(String lang) {
		try {
			return (List<FAQLangue>)em.createQuery("FROM FAQLangue f WHERE f.langue.code = '" + lang + "'").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

}
