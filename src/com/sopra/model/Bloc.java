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
	private int id;
	
	@Column(name="blo_x")
	@NotNull
	private int x;
	
	@Column(name="blo_y")
	@NotNull
	private int y;
	
	@ManyToOne
	@JoinColumn(name="blo_figure_id")
	private Figure figure;
	

	public int getId() {
		return id;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Figure getFigure() {
		return figure;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setFigure(Figure figure) {
		this.figure = figure;
	}

}
