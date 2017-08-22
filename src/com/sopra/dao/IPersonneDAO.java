package com.sopra.dao;

import com.sopra.model.Personne;

public interface IPersonneDAO extends IDAO<Personne> {
	public Personne findByUsername(String username);
}
