package com.epam.easyshopway.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.CupboardProductInformation;

public class CupboardProductsInformationDTO extends SuperDTO {
	private final String SELECT_PRODUCTS_BY_CUPBOARD_ID = "SELECT product.id, product_placement.cupboard_id, product.name_en, product.name_uk, product_type.img "
			+ "FROM product INNER JOIN product_type "
			+ "ON product.product_type_id = product_type.id "
			+ "INNER JOIN product_placement ON product_placement.product_id = product.id "
			+ "where product_placement.cupboard_id = ? and product.active = '1'";

	public List<CupboardProductInformation> getAllProductbyCupdoardId(
			Integer cupboardId) throws SQLException, InstantiationException,
			IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_PRODUCTS_BY_CUPBOARD_ID);
		statement.setInt(1, cupboardId);
		ResultSet resultSet = statement.executeQuery();
		List<CupboardProductInformation> users = new Transformer<CupboardProductInformation>(
				CupboardProductInformation.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}

}
