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
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="personne", uniqueConstraints=@UniqueConstraint(columnNames="per_username"))
@Inheritance(strategy=InheritanceType.JOINED)
public class Personne implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="per_id")
	private int id;
	
	@Column(name="per_username")
	@NotNull
	@NotEmpty(message="Merci de renseigner le nom d'utilisateur")
	private String username;
	
	@Column(name="per_password")
	@NotNull
	@NotEmpty(message="Merci de renseigner le mot de passe")
	private String password;
	
	@Column(name="per_nom")
	@NotEmpty(message="Merci de renseigner le nom")
	private String nom;
	
	@Column(name="per_prenom")
	@NotEmpty(message="Merci de renseigner le prénom")
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
	
	public int getType() { return 0; }
	

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
