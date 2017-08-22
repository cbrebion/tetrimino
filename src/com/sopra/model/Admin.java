package com.sopra.model;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name="admin")
@PrimaryKeyJoinColumn(name="adm_id", referencedColumnName="per_id")
public class Admin extends Personne {
	private static final long serialVersionUID = 1L;
	
	public Admin() {}
	
	public int getType() {
		return 1;
	}

}
