package com.epam.easyshopway.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductInformation;

public class ProductInformationDTO extends SuperDTO {
	private final String SELECT_ALL_PRODUCTS = "SELECT product.id, product.name_en, product.name_uk, product_type.img "
			+ "FROM product INNER JOIN product_type "
			+ "ON product.product_type_id = product_type.id";

	public List<ProductInformation> getAllProduct() throws SQLException,
			InstantiationException, IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_ALL_PRODUCTS);
		ResultSet resultSet = statement.executeQuery();
		List<ProductInformation> users = new Transformer<ProductInformation>(
				ProductInformation.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}
}
