package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductType {
	@Column("id")
	private Integer id;

	@Column("name_en")
	private String nameEn;

	@Column("name_uk")
	private String nameUk;

	@Column("img")
	private String imageUrl;

	@Column("active")
	private boolean active;

	public ProductType() {
		super();
	}

	public ProductType(String nameEn, String nameUk, String imageUrl,
			boolean active) {
		super();
		this.nameEn = nameEn;
		this.nameUk = nameUk;
		this.imageUrl = imageUrl;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
