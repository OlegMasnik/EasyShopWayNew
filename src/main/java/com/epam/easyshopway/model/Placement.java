package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Placement {
	@Column("id")
	private Integer id;

	@Column("map_id")
	private Integer mapId;

	@Column("value")
	private Integer value;

	public Placement(Integer id, Integer mapId, Integer value) {
		super();
		this.id = id;
		this.mapId = mapId;
		this.value = value;
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

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}
	
}
