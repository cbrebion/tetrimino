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
@Table(name="langue")
public class Langue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="lan_id")
	protected Integer id;
	
	@Column(name="lan_code")
	@NotNull
	protected String code;
	
	@JsonIgnore
	@OneToMany(mappedBy="langue")
	protected List<FAQLangue> faqLangues;

	public Integer getId() {
		return id;
	}

	public String getCode() {
		return code;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<FAQLangue> getFAQLangues() {
		return faqLangues;
	}

	public void setFAQLangues(List<FAQLangue> fAQLangues) {
		faqLangues = fAQLangues;
	}

	public List<FAQLangue> getFaqLangues() {
		return faqLangues;
	}

	public void setFaqLangues(List<FAQLangue> faqLangues) {
		this.faqLangues = faqLangues;
	}

}
