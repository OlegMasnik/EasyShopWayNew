package com.epam.easyshopway.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.FullProductList;

public class FullProductListDTO extends SuperDTO {
	private final String SELECT_User_Product_Lists = "SELECT product_list.date,  product_list.time,  product.name_uk,  product.name_en,"
			+ "  product_list.id,  product_type.img,  product_type.name_en,  product_type.name_uk FROM product_list"
			+ " JOIN list_and_product  ON product_list.id = list_and_product.product_list_id JOIN product"
			+ " ON list_and_product.product_id = product.id   JOIN product_type  "
			+ "ON product.product_type_id = product_type.id WHERE product_list.user_id LIKE ? "
			+ "ORDER BY product_list.date, product_list.time";

	public List<FullProductList> getProductListByUserId(Integer id)
			throws SQLException, InstantiationException, IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_User_Product_Lists);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<FullProductList> users = new Transformer<FullProductList>(
				FullProductList.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}
}
