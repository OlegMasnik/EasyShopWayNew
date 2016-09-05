package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;
@Deprecated
public class Entry {
	@Column("id")
	private Integer id;

	@Column("map_id")
	private Integer mapId;

	@Column("place")
	private Integer place;

	public Entry() {
		super();
	}

	public Entry(Integer id, Integer mapId, Integer place) {
		super();
		this.id = id;
		this.mapId = mapId;
		this.place = place;
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
}
