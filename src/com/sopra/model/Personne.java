package com.sopra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name="personne")
@Inheritance(strategy=InheritanceType.JOINED)
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="per_id")
	private int id;
	
	@Column(name="per_username")
	@NotNull
	@Size(min=2, max=30)
	private String username;
	
	@Column(name="per_password")
	@NotNull
	@Size(min=2, max=30)
	private String password;
	
	@Column(name="per_nom")
	@Size(min=2, max=100)
	private String nom;
	
	@Column(name="per_prenom")
	@Size(min=2, max=100)
	private String prenom;

	
	public Personne() {}
	
	public int getId() {
		return id;
	}

	public String getUsername() {
		return username;
	}

	public String getPassword() {
		return password;
	}

	public String getNom() {
		return nom;
	}

	public String getPrenom() {
		return prenom;
	}
	

	public void setId(int id) {
		this.id = id;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
}
