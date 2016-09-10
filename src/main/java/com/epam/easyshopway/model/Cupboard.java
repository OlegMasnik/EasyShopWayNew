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

	public Cupboard() {
		super();
	}

	public Cupboard(Integer boardAmount, String descriptionEn,
			String descriptionUk, Boolean active) {
		super();
		this.boardAmount = boardAmount;
		this.descriptionEn = descriptionEn;
		this.descriptionUk = descriptionUk;
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

}
