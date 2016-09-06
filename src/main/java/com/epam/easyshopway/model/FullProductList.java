package com.epam.easyshopway.model;

import java.sql.Date;
import java.sql.Time;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class FullProductList {
	@Column("product_list.id")
	private Integer id;

	@Column("product_list.date")
	private Date data;

	@Column("product_list.time")
	private Time time;

	@Column("product.name_uk")
	private String productNameUk;

	@Column("product.name_en")
	private String productNameEn;

	@Column("product_type.img")
	private String image;

	@Column("product_type.name_en")
	private String typeNameEn;

	@Column("product_type.name_uk")
	private String typeNameUk;

	public FullProductList() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Date getData() {
		return data;
	}

	public void setData(String data) {
		this.data = Date.valueOf(data);
	}

	public Time getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = Time.valueOf(time);
	}

	public String getProductNameUk() {
		return productNameUk;
	}

	public void setProductNameUk(String productNameUk) {
		this.productNameUk = productNameUk;
	}

	public String getProductNameEn() {
		return productNameEn;
	}

	public void setProductNameEn(String productNameEn) {
		this.productNameEn = productNameEn;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTypeNameEn() {
		return typeNameEn;
	}

	public void setTypeNameEn(String typeNameEn) {
		this.typeNameEn = typeNameEn;
	}

	public String getTypeNameUk() {
		return typeNameUk;
	}

	public void setTypeNameUk(String typeNameUk) {
		this.typeNameUk = typeNameUk;
	}

}
