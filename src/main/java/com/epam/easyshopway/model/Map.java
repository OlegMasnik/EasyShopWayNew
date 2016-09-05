package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Map {
	@Column("id")
	private Integer id;

	@Column("weight")
	private Integer weight;

	@Column("height")
	private Integer height;

	public Map() {
		super();
	}

	public Map(Integer weight, Integer height) {
		super();
		this.weight = weight;
		this.height = height;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getWeight() {
		return weight;
	}

	public void setWeight(Integer weight) {
		this.weight = weight;
	}

	public Integer getHeight() {
		return height;
	}

	public void setHeight(Integer height) {
		this.height = height;
	}

}
