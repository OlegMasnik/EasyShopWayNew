package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductPlacement {
	@Column("id")
	private Integer id;

	@Column("product_id")
	private Integer productId;

	@Column("cupboard_id")
	private Integer cupboardId;

	@Column("place")
	private Integer place;

	public ProductPlacement(Integer id, Integer productId, Integer cupboardId,
			Integer place) {
		super();
		this.id = id;
		this.productId = productId;
		this.cupboardId = cupboardId;
		this.place = place;
	}

	public ProductPlacement() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Integer getCupboardId() {
		return cupboardId;
	}

	public void setCupboardId(Integer cupboardId) {
		this.cupboardId = cupboardId;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

}
