package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Cupboard {
	@com.epam.easyshopway.dao.transformer.annotation.Column("id")
	private Integer id;

	@Column("board_amount")
	private Integer boardAmount;

	@Column("description_en")
	private String descriptionEn;

	@Column("description_uk")
	private String descriptionUk;

	@Column("active")
	private Boolean active;

	public Cupboard() {
		super();
	}

	public Cupboard(Integer id, Integer boardAmount, String descriptionEn, String descriptionUk, Boolean active) {
		super();
		this.id = id;
		this.boardAmount = boardAmount;
		this.descriptionEn = descriptionEn;
		this.descriptionUk = descriptionUk;
		this.active = active;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getBoardAmount() {
		return boardAmount;
	}

	public void setBoardAmount(Integer boardAmount) {
		this.boardAmount = boardAmount;
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

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}
}
