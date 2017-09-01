package com.sopra.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

public class PartieJSON extends Partie {
	private static final long serialVersionUID = 1L;

	@JsonProperty("scores")
	public List<Score> getAllScores() {
		return this.getScores();
	}
	
	public PartieJSON(Partie partie) {
		this.id = partie.getId();
		this.finie = partie.getFinie();
		this.type = partie.getType();
		this.joueur1 = partie.getJoueur1();
		this.joueur2 = partie.getJoueur2();
		this.scores = partie.getScores();
	}
	
}
