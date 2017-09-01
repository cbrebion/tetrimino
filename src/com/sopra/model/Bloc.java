package com.sopra.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name="bloc")
public class Bloc implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="blo_id")
	protected Integer id;
	
	@Column(name="blo_x")
	@NotNull
	protected Integer x;
	
	@Column(name="blo_y")
	@NotNull
	protected Integer y;
	
	@ManyToOne
	@JoinColumn(name="blo_figure_id")
	protected Figure figure;
	

	public Integer getId() {
		return id;
	}

	public Integer getX() {
		return x;
	}

	public Integer getY() {
		return y;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public void setX(Integer x) {
		this.x = x;
	}

	public void setY(Integer y) {
		this.y = y;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

}
