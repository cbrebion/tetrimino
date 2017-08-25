package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sopra.dao.IAdminDAO;
import com.sopra.model.Admin;

@Repository
@Transactional
public class AdminHibernateDAO implements IAdminDAO {

	@PersistenceContext
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Admin> findAll() {
		try {
			return (List<Admin>)em.createQuery("FROM Admin").getResultList();
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Admin find(int id) {
		try {
			return em.find(Admin.class, id);
		} catch (NoResultException e) {
			return null;
		}
	}

	@Override
	public Admin save(Admin admin) {
		return em.merge(admin);
	}

	@Override
	public void delete(Admin admin) {
		em.remove(em.merge(admin));

	}

}
