package com.sopra.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="joueur")
@PrimaryKeyJoinColumn(name="jou_id", referencedColumnName="per_id")
public class Joueur extends Personne {
	private static final long serialVersionUID = 1L;
	
	@OneToMany(mappedBy="joueur")
	private List<Partie> parties;
	
	
	public List<Partie> getParties() {
		return parties;
	}
	
	public int getType() {
		return 2;
	}
	
	public void setParties(List<Partie> parties) {
		this.parties = parties;
	}

}
