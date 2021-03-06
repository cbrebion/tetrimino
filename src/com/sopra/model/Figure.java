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
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name="figure")
public class Figure implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="fig_id")
	private Integer id;
	
	@Column(name="fig_ordre_rotation")
	@NotNull(message="Merci de renseigner un ordre de rotation")
	@JsonProperty("ordre")
	private Integer ordreRotation;
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name="fig_tetrimino_id")
	private Tetrimino tetrimino;
	
	@OneToMany(mappedBy="figure")
	private List<Bloc> blocs;

	
	public Integer getId() {
		return id;
	}

	public Integer getOrdreRotation() {
		return ordreRotation;
	}

	public Tetrimino getTetrimino() {
		return tetrimino;
	}

	public List<Bloc> getBlocs() {
		return blocs;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setOrdreRotation(Integer ordreRotation) {
		this.ordreRotation = ordreRotation;
	}

	public void setTetrimino(Tetrimino tetrimino) {
		this.tetrimino = tetrimino;
	}

	public void setBlocs(List<Bloc> blocs) {
		this.blocs = blocs;
	}
	
	public void addBloc(Bloc bloc) {
		this.blocs.add(bloc);
	}

}
