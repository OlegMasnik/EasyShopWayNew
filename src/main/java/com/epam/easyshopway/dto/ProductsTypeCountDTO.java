package com.epam.easyshopway.dto;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductsTypeCount;

public class ProductsTypeCountDTO extends SuperDTO{
	private final String SELECT_PRODUCT_TYPES = "SELECT pt.name_en, pt.name_uk, COUNT(*) AS count FROM product_list pl JOIN list_and_product lap ON pl.id = lap.product_list_id JOIN product p ON lap.product_id = p.id JOIN product_type pt ON p.product_type_id = pt.id WHERE pl.user_id = ? AND date BETWEEN ? AND ? GROUP BY pt.name_en ORDER BY count DESC;";
	
	public List<ProductsTypeCount> getUserProductTypes(Integer id, Date startDate, Date endDate) throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_TYPES);
		statement.setInt(1, id);
		statement.setDate(2, startDate);
		statement.setDate(3, endDate);
		ResultSet resultSet = statement.executeQuery();
		List<ProductsTypeCount> productTypes = new Transformer<ProductsTypeCount>(ProductsTypeCount.class).fromRStoCollection(resultSet);
		statement.close();
		return productTypes;
	}
}
