package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Placement {
	@Column("id")
	private Integer id;

	@Column("map_id")
	private Integer mapId;

	@Column("place")
	private Integer place;

	@Column("type")
	private String type;

	public Placement(Integer id, Integer mapId, Integer place, String type) {
		super();
		this.id = id;
		this.mapId = mapId;
		this.place = place;
		this.type = type;
	}

	public Placement() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getMapId() {
		return mapId;
	}

	public void setMapId(Integer mapId) {
		this.mapId = mapId;
	}

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
