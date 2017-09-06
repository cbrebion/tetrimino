package com.sopra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="faqlangue")
public class FAQLangue implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="faql_id")
	protected Integer id;
	
	@Column(name="faql_question")
	@NotNull
	@NotEmpty(message="Veuillez renseigner une question")
	protected String question;
	
	@Column(name="faql_reponse", columnDefinition="text")
	@NotNull
	@NotEmpty(message="Veuillez renseigner une réponse")
	protected String reponse;
	
	@ManyToOne
	@JoinColumn(name="faql_langue_id")
	protected Langue langue;
	
	@ManyToOne
	@JoinColumn(name="faql_faq_id")
	@OrderBy("libelle")
	protected FAQ faq;
	
	public Integer getId() {
		return id;
	}

	public String getQuestion() {
		return question;
	}

	public String getReponse() {
		return reponse;
	}

	public Langue getLangue() {
		return langue;
	}

	public FAQ getFaq() {
		return faq;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public void setReponse(String reponse) {
		this.reponse = reponse;
	}

	public void setLangue(Langue langue) {
		this.langue = langue;
	}

	public void setFaq(FAQ faq) {
		this.faq = faq;
	}

}
