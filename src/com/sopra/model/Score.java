package com.sopra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="score")
public class Score implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="sco_id")
	private Integer id;
	
	@Column(name="sco_points")
	private Integer points;
	
	@Column(name="sco_lines")
	private Integer lines;
	
	@Column(name="sco_level")
	private Integer level;
	
	@ManyToOne
	@JoinColumn(name="sco_joueur_id")
	private Joueur joueur;
	
	@ManyToOne
	@JoinColumn(name="sco_partie_id")
	private Partie partie;

	public Integer getId() {
		return id;
	}

	public Integer getLines() {
		return lines;
	}

	public Integer getLevel() {
		return level;
	}

	public Joueur getJoueur() {
		return joueur;
	}

	public Partie getPartie() {
		return partie;
	}
	
	public Integer getPoints() {
		return points;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setLines(Integer lines) {
		this.lines = lines;
	}

	public void setLevel(Integer level) {
		this.level = level;
	}

	public void setJoueur(Joueur joueur) {
		this.joueur = joueur;
	}

	public void setPartie(Partie partie) {
		this.partie = partie;
	}
	
	public void setPoints(Integer points) {
		this.points = points;
	}
}
