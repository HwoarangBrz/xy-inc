package com.xy.inc.poi.api.dtos;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Length;

public class PointOfInterestDto {

	private Integer id;
	private String name;
	private Integer coord_x;
	private Integer coord_y;
	
	public PointOfInterestDto() {
	}

	public PointOfInterestDto(Integer id, String name, Integer coord_x, Integer coord_y) {
		this.id = id;
		this.name = name;
		this.coord_x = coord_x;
		this.coord_y = coord_y;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@NotNull(message = "Name can't be empty.")
	@Length(min = 3, max = 50, message = "Name must contain between 3 and 50 characters.")
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@NotNull(message = "Coordinate invalid")
	@Min(value = 0, message = "The value must be positive")
	public Integer getCoord_x() {
		return coord_x;
	}

	public void setCoord_x(Integer coord_x) {
		this.coord_x = coord_x;
	}

	@NotNull(message = "Coordinate invalid")
	@Min(value = 0, message = "The value must be positive")
	public Integer getCoord_y() {
		return coord_y;
	}

	public void setCoord_y(Integer coord_y) {
		this.coord_y = coord_y;
	}

	@Override
	public String toString() {
		return "PointOfInterestDto [id=" + id + ", name=" + name + ", coord_x=" + coord_x + ", coord_y=" + coord_y
				+ "]";
	}
}
