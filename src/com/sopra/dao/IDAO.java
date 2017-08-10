package com.sopra.dao;

import java.util.List;

public interface IDAO <T> {
	//public T enregistrer(T obj);
	public List<T> rechercher();
	public T rechercher(int id);
	public T modifier(T obj);
	public void supprimer(int id);
}
