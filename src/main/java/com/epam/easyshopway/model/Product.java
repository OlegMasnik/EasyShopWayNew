package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Product {
	@Column("id")
	private Integer id;

	@Column("product_type_id")
	private Integer productTypeId;

	@Column("name_uk")
	private String nameUk;

	@Column("name_en")
	private String nameEn;

	@Column("active")
	private boolean active;

	public Product() {
		super();
	}

	public Product(Integer productTypeId, String nameUk, String nameEn,
			boolean active) {
		super();
		this.productTypeId = productTypeId;
		this.nameUk = nameUk;
		this.nameEn = nameEn;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductTypeId() {
		return productTypeId;
	}

	public void setProductTypeId(Integer productTypeId) {
		this.productTypeId = productTypeId;
	}

	public String getNameUk() {
		return nameUk;
	}

	public void setNameUk(String nameUk) {
		this.nameUk = nameUk;
	}

	public String getNameEn() {
		return nameEn;
	}

	public void setNameEn(String nameEn) {
		this.nameEn = nameEn;
	}

	public boolean isActive() {
		return active;
	}

	public void setActive(boolean active) {
		this.active = active;
	}

}
