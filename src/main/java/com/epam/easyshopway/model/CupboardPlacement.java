package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class CupboardPlacement {
	@Column("id")
	private Integer id;

	@Column("map_id")
	private Integer mapId;

	@Column("cupboard_id")
	private Integer cupboardId;

	@Column("x_start")
	private Integer xStart;

	@Column("x_end")
	private Integer xEnd;

	public CupboardPlacement() {
		super();
	}

	public CupboardPlacement(Integer id, Integer mapId, Integer cupboardId,
			Integer xStart, Integer xEnd) {
		super();
		this.id = id;
		this.mapId = mapId;
		this.cupboardId = cupboardId;
		this.xStart = xStart;
		this.xEnd = xEnd;
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

	public Integer getCupboardId() {
		return cupboardId;
	}

	public void setCupboardId(Integer cupboardId) {
		this.cupboardId = cupboardId;
	}

	public Integer getXStart() {
		return xStart;
	}

	public void setXStart(Integer xStart) {
		this.xStart = xStart;
	}

	public Integer getXEnd() {
		return xEnd;
	}

	public void setXEnd(Integer xEnd) {
		this.xEnd = xEnd;
	}

}
