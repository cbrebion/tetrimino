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

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="faq")
public class FAQ implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="faq_id")
	protected Integer id;
	
	@Column(name="faq_libelle")
	@NotNull
	protected String libelle;
	
	@JsonIgnore
	@OneToMany(mappedBy="faq")
	protected List<FAQLangue> faqLangues;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public List<FAQLangue> getFaqLangues() {
		return faqLangues;
	}

	public void setFaqLangues(List<FAQLangue> faqLangues) {
		this.faqLangues = faqLangues;
	}

	public String getLibelle() {
		return libelle;
	}

	public void setLibelle(String libelle) {
		this.libelle = libelle;
	}

}
