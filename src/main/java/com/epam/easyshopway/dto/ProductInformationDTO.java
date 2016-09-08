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
			+ "ON product.product_type_id = product_type.id where product.active = '1' ";
	private final String SELECT_PRODUCTS_BY_USER_LIST_ID = "SELECT product.id, product.name_en, product.name_uk, product_type.img "
			+ "FROM product INNER JOIN product_type "
			+ "ON product.product_type_id = product_type.id "
			+ "INNER JOIN list_and_product ON product.id = list_and_product.product_id "
			+ "where list_and_product.product_list_id = ? and product.active = '1'";
	private final String SELECT_PRODUCTS_BY_CUPBOARD_ID = "SELECT product.id, product.name_en, product.name_uk, product_type.img "
			+ "FROM product INNER JOIN product_type "
			+ "ON product.product_type_id = product_type.id "
			+ "INNER JOIN product_placement ON product_placement.product_id = product.id "
			+ "where product_placement.cupboard_id = ? and product.active = '1'";

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

	public List<ProductInformation> getAllProductbyProductListId(
			Integer productListId) throws SQLException, InstantiationException,
			IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_PRODUCTS_BY_USER_LIST_ID);
		statement.setInt(1, productListId);
		ResultSet resultSet = statement.executeQuery();
		List<ProductInformation> users = new Transformer<ProductInformation>(
				ProductInformation.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}

	public List<ProductInformation> getAllProductbyCupdoardId(
			Integer productListId) throws SQLException, InstantiationException,
			IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_PRODUCTS_BY_CUPBOARD_ID);
		statement.setInt(1, productListId);
		ResultSet resultSet = statement.executeQuery();
		List<ProductInformation> users = new Transformer<ProductInformation>(
				ProductInformation.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}
}
