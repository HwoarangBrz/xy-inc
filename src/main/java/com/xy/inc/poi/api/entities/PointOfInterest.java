package com.xy.inc.poi.api.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="poi")
public class PointOfInterest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String name;
	private Integer coord_x;
	private Integer coord_y;
	
	public PointOfInterest() {
	}

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "name", nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "coord_x", nullable = false)
	public Integer getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(Integer coord_x) {
		this.coord_x = coord_x;
	}

	@Column(name = "coord_y", nullable = false)
	public Integer getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(Integer coord_y) {
		this.coord_y = coord_y;
	}

	@Override
	public String toString() {
		return "PointOfInterest [id=" + id + ", name=" + name + ", coord_x=" + coord_x + ", coord_y=" + coord_y + "]";
	}
}
