package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Wall {
	@Column("id")
	private Integer id;

	@Column("placement_id")
	private Integer placementId;

	public Wall() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getPlacementId() {
		return placementId;
	}

	public void setPlacementId(Integer placementId) {
		this.placementId = placementId;
	}

}
