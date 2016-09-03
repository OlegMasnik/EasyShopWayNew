package com.epam.easyshopway.model;

import java.sql.Date;
import java.sql.Time;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductList {
	@Column("id")
	private Integer id;

	@Column("user_id")
	private Integer userId;

	@Column("product_id")
	private Integer productId;

	@Column("date")
	private Date date;

	@Column("time")
	private Time time;

	public ProductList() {
		super();
	}

	public ProductList(Integer id, Integer userId, Integer productId, Date date, Time time) {
		super();
		this.id = id;
		this.userId = userId;
		this.productId = productId;
		this.date = date;
		this.time = time;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public Integer getProductId() {
		return productId;
	}

	public void setProductId(Integer productId) {
		this.productId = productId;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = Date.valueOf(date);
	}

	public Time getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = Time.valueOf(time);
	}

}
