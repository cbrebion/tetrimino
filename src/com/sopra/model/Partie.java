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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="partie")
public class Partie implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="par_id")
	protected Integer id;
	
	@Column(name="par_finie")
	@NotNull
	protected Boolean finie;
	
	@Column(name="par_type")
	@NotNull
	protected Boolean type;
	
	@ManyToOne
	@JoinColumn(name="par_joueur1_id")
	protected Joueur joueur1;
	
	@ManyToOne
	@JoinColumn(name="par_joueur2_id")
	protected Joueur joueur2;
	
	@JsonIgnore
	@OneToMany(mappedBy="partie")
	protected List<Score> scores;

	
	public Partie() {}
	
	
	public Integer getId() {
		return id;
	}

	public Boolean getFinie() {
		return finie;
	}

	public Joueur getJoueur1() {
		return joueur1;
	}

	public Joueur getJoueur2() {
		return joueur2;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setFinie(Boolean finie) {
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


	public Boolean getType() {
		return type;
	}


	public void setType(Boolean type) {
		this.type = type;
	}

}
