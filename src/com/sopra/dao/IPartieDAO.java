package com.sopra.dao;

import java.util.List;

import com.sopra.model.Partie;

public interface IPartieDAO extends IDAO<Partie> {
	public List<Partie> findAllDesc();
	public List<Partie> findAllWithScores();
}
