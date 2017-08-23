package com.sopra.dao.hibernate;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sopra.dao.IScoreDAO;
import com.sopra.model.Score;

@Stateless
public class ScoreHibernateDAO implements IScoreDAO {

	@PersistenceContext
	private EntityManager em;
	
	@Override
	public List<Score> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Score find(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Score save(Score obj) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void delete(Score obj) {
		// TODO Auto-generated method stub

	}

}
