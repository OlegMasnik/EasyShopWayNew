package com.epam.easyshopway.model;

import java.util.List;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductInformation {

	@Column("product.id")
	private Integer id;

	@Column("product.name_uk")
	private String productNameUk;

	@Column("product.name_en")
	private String productNameEn;

	@Column("product_type.img")
	private String image;

	private List<Integer> coordinates;

	public ProductInformation() {
		super();
	}

	public ProductInformation(String productNameUk, String productNameEn,
			String image) {
		super();
		this.productNameUk = productNameUk;
		this.productNameEn = productNameEn;
		this.image = image;
		this.coordinates = ProductCoordinate.getProductCoordinatesOnMap(id);
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
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

	public List<Integer> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates() {
		this.coordinates = ProductCoordinate.getProductCoordinatesOnMap(id);
	}

}
