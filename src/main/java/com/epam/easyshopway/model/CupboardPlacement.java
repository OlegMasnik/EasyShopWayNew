package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class CupboardPlacement {
	@Column("id")
	private Integer id;

	@Column("cupboard_id")
	private Integer cupboardId;

	@Column("placement_id")
	private Integer placementId;

	public CupboardPlacement() {
		super();
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getCupboardId() {
		return cupboardId;
	}

	public void setCupboardId(Integer cupboardId) {
		this.cupboardId = cupboardId;
	}

	public Integer getPlacementId() {
		return placementId;
	}

	public void setPlacementId(Integer placementId) {
		this.placementId = placementId;
	}
	
}
