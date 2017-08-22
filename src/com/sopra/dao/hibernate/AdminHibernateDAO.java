package com.sopra.dao.hibernate;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IAdminDAO;
import com.sopra.model.Admin;

public class AdminHibernateDAO implements IAdminDAO {

	@PersistenceContext
	EntityManager em;
	
	@Override
	public List<Admin> findAll() {
		return (List<Admin>)em.createQuery("FROM Admin").getResultList();
	}

	@Override
	public Admin find(int id) {
		return em.find(Admin.class, id);
	}

	@Override
	public Admin save(Admin admin) {
		return em.merge(admin);
	}

	@Override
	public void delete(Admin admin) {
		em.remove(admin);

	}

}
