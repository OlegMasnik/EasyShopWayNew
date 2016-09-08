package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class CupboardInformation {
	@Column("map_id")
	private Integer mapId;
	
	@Column("cupboard_id")
	private Integer cupboardId;

	@Column("place")
	private Integer place;
	
	@Column("description_en")
	private String descriptionEn;
	
	@Column("description_uk")
	private String descriptionUk;
	
	@Column("board_amount")
	private Integer boardAmount;

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

	public Integer getPlace() {
		return place;
	}

	public void setPlace(Integer place) {
		this.place = place;
	}

	public String getDescriptionEn() {
		return descriptionEn;
	}

	public void setDescriptionEn(String descriptionEn) {
		this.descriptionEn = descriptionEn;
	}

	public String getDescriptionUk() {
		return descriptionUk;
	}

	public void setDescriptionUk(String descriptionUk) {
		this.descriptionUk = descriptionUk;
	}

	public Integer getBoardAmount() {
		return boardAmount;
	}

	public void setBoardAmount(Integer boardAmount) {
		this.boardAmount = boardAmount;
	}
}
