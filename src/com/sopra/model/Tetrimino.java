package com.sopra.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Entity
@Table(name="tetrimino")
public class Tetrimino implements Serializable
{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="tet_id")
	private int id;
	
	@Column(name="tet_nom")
	@NotNull
	@Size(max=30)
	private String nom;
	
	@Column(name="tet_couleur")
	@NotNull
	@Size(max=30)
	private String couleur;
	
	@Column(name="tet_coeff")
	@NotNull
	private double coeff;
	
	@OneToMany(mappedBy="tetrimino")
	private List<Figure> figures;
	
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getCouleur() {
		return couleur;
	}
	
	public void setCouleur(String couleur) {
		this.couleur = couleur;
	}
	
	
	public Tetrimino() {}
	
	public Tetrimino(String nom, String couleur) {
		this.nom = nom;
		this.couleur = couleur;
	}

	public List<Figure> getFigures() {
		return figures;
	}

	public void setFigures(List<Figure> figures) {
		this.figures = figures;
	}

	public double getCoeff() {
		return coeff;
	}

	public void setCoeff(double coeff) {
		this.coeff = coeff;
	}
}