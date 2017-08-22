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
	
	@OneToMany(mappedBy="joueur1")
	private List<Partie> partiesJ1;
	
	@OneToMany(mappedBy="joueur2")
	private List<Partie> partiesJ2;
	
	
	public List<Partie> getPartiesJ1() {
		return partiesJ1;
	}
	
	public List<Partie> getPartiesJ2() {
		return partiesJ2;
	}
	
	public int getType() {
		return 2;
	}
	
	public void setPartiesJ1(List<Partie> parties) {
		this.partiesJ1 = parties;
	}
	
	public void setPartiesJ2(List<Partie> parties) {
		this.partiesJ2 = parties;
	}

}
