package com.epam.easyshopway.model;

import java.util.List;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class CupboardProductInformation {

	@Column("product_placement.cupboard_id")
	private Integer cupboardId;

	@Column("product.id")
	private Integer id;

	@Column("product.name_uk")
	private String productNameUk;

	@Column("product.name_en")
	private String productNameEn;

	@Column("product_type.img")
	private String image;

	private List<Integer> coordinatesOnCupboard;

	public CupboardProductInformation(Integer cupboardId, String productNameUk,
			String productNameEn, String image) {
		super();
		this.cupboardId = cupboardId;
		this.productNameUk = productNameUk;
		this.productNameEn = productNameEn;
		this.image = image;
		this.coordinatesOnCupboard = ProductCoordinate
				.getProductCoordinatesOnCupboard(id, cupboardId);
	}

	public CupboardProductInformation() {
		super();
	}

	public Integer getCupboardId() {
		return cupboardId;
	}

	public void setCupboardId(Integer cupboardId) {
		this.cupboardId = cupboardId;
		setCoordinatesOnCupboard();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
		setCoordinatesOnCupboard();
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

	public List<Integer> getCoordinatesOnCupboard() {
		return coordinatesOnCupboard;
	}

	public void setCoordinatesOnCupboard() {
		if ((id != null) & (cupboardId != null)) {
			this.coordinatesOnCupboard = ProductCoordinate
					.getProductCoordinatesOnCupboard(id, cupboardId);
		}
	}

}
