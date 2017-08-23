package com.sopra.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="partie")
public class Partie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="par_id")
	private int id;
	
	@Column(name="par_score")
	@NotNull
	private int score;
	
	@Column(name="par_finie")
	@NotNull
	private boolean finie;
	
	@ManyToOne
	@JoinColumn(name="par_joueur1")
	private Joueur joueur1;
	
	@ManyToOne
	@JoinColumn(name="par_joueur2")
	private Joueur joueur2;
	
	@OneToMany(mappedBy="partie")
	private List<Score> scores;

	
	public Partie() {}
	
	
	public int getId() {
		return id;
	}

	public int getScore() {
		return score;
	}

	public boolean isFinie() {
		return finie;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setFinie(boolean finie) {
		this.finie = finie;
	}

	public void setJoueur1(Joueur joueur1) {
		this.joueur1 = joueur1;
	}

	public void setJoueur2(Joueur joueur2) {
		this.joueur2 = joueur2;
	}


	public List<Score> getScores() {
		return scores;
	}


	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

}
