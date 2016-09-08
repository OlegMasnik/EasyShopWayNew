package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Map {
	@Column("id")
	private Integer id;

	@Column("weight")
	private Integer weight;

	@Column("height")
	private Integer height;
	
	@Column("name_en")
	private String nameEn;
	
	@Column("name_uk")
	private String nameUk;

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

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public String getNameUk() {
		return nameUk;
	}

	public void setNameUk(String nameUk) {
		this.nameUk = nameUk;
	}
}
