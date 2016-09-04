package com.epam.easyshopway.model;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class Placement {
	@Column("id")
	private Integer id;

	@Column("map_id")
	private Integer mapId;

	@Column("place")
	private Integer place;
}
