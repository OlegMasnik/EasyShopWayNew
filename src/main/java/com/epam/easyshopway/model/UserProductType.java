package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class UserProductType {
	@Column("id")
	private Integer id;

	@Column("name_en")
	private String nameEnglish;

	@Column("name_uk")
	private String nameUkrainian;

	@Column("count")
	private Integer count;

	public String getNameEnglish() {
		return nameEnglish;
	}

	public void setNameEnglish(String nameEnglish) {
		this.nameEnglish = nameEnglish;
	}

	public String getNameUkrainian() {
		return nameUkrainian;
	}

	public void setNameUkrainian(String nameUkrainian) {
		this.nameUkrainian = nameUkrainian;
	}

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
