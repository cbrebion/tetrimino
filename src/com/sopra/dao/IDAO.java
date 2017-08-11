package com.sopra.dao;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

public interface IDAO <T> {
	public void enregistrer(HttpServletRequest req, T obj);
	public List<T> rechercher(HttpServletRequest req);
	public T rechercher(HttpServletRequest req, int id);
	public T modifier(T obj);
	public void supprimer(HttpServletRequest req, int id);
}
