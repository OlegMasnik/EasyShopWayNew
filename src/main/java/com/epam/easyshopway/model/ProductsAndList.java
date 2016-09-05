package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductsAndList {
	@Column("id")
	private Integer id;

	@Column("product_id")
	private Integer productId;

	@Column("product_list_id")
	private Integer productListId;

	public ProductsAndList() {
		super();
	}

	public ProductsAndList(Integer productId, Integer productListId) {
		super();
		this.productId = productId;
		this.productListId = productListId;
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

	public Integer getProductListId() {
		return productListId;
	}

	public void setProductListId(Integer productListId) {
		this.productListId = productListId;
	}

}
