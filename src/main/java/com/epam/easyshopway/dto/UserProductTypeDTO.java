package com.epam.easyshopway.dto;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.UserProductType;

public class UserProductTypeDTO extends SuperDTO{
	private final String SELECT_PRODUCT_TYPES = "SELECT pt.name_en, COUNT(*) FROM product_list pl JOIN product p ON pl.product_id = p.id JOIN product_type pt ON p.product_type_id = pt.id WHERE pl.user_id = ? GROUP BY pt.name_en ORDER BY pt.name_en ASC;";
	
	public List<UserProductType> getUserProductTypes(Integer id) throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_PRODUCT_TYPES);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<UserProductType> users = new Transformer<UserProductType>(UserProductType.class).fromRStoCollection(resultSet);
		statement.close();
		return users;
	}
}
