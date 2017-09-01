package com.sopra.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="joueur")
@PrimaryKeyJoinColumn(name="jou_id", referencedColumnName="per_id")
public class Joueur extends Personne {
	private static final long serialVersionUID = 1L;
	
	@Column(name="jou_banni")
	@NotNull
	protected Boolean banni;
	
	@JsonIgnore
	@OneToMany(mappedBy="joueur1")
	protected List<Partie> partiesJ1;
	
	@JsonIgnore
	@OneToMany(mappedBy="joueur2")
	protected List<Partie> partiesJ2;
	
	@JsonIgnore
	@OneToMany(mappedBy="joueur")
	protected List<Score> scores;
	
	
	public Joueur() {
		super();
		this.banni = false;
	}
	
	
	public Boolean getBanni() {
		return banni;
	}
	
	public List<Partie> getPartiesJ1() {
		return partiesJ1;
	}
	
	public List<Partie> getPartiesJ2() {
		return partiesJ2;
	}
	
	public Integer getType() {
		return 2;
	}
	
	public void setBanni(Boolean banni) {
		this.banni = banni;
	}
	
	public void setPartiesJ1(List<Partie> parties) {
		this.partiesJ1 = parties;
	}
	
	public void setPartiesJ2(List<Partie> parties) {
		this.partiesJ2 = parties;
	}


	public List<Score> getScores() {
		return scores;
	}


	public void setScores(List<Score> scores) {
		this.scores = scores;
	}

}
