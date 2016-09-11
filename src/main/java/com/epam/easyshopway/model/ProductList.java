package com.epam.easyshopway.model;

import java.sql.Date;
import java.sql.Time;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class ProductList {
	@Column("id")
	private Integer id;

	@Column("user_id")
	private Integer userId;

	@Column("date")
	private Date date;

	@Column("time")
	private Time time;
	@Column("product_list.map_id")
	private Integer mapId;

	public ProductList() {
		super();
	}

	public ProductList(Integer userId, Date date, Time time, Integer mapId) {
		super();

		this.userId = userId;
		this.date = date;
		this.time = time;
		this.mapId = mapId;
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

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

}
