package com.epam.easyshopway.dto;

import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.DiagramShopCount;

public class DiagramShopCountDTO extends SuperDTO {
	private final String SELECT_SHOP_COUNT_ADMIN = "" + "SELECT map.name_uk, map.name_en, COUNT(*) AS count "
			+ "FROM map  INNER JOIN product_list ON product_list.map_id = map.id "
			+ "WHERE product_list.date BETWEEN ? AND ? " + "GROUP BY map.name_en ORDER BY count DESC;";

	private final String SELECT_SHOP_COUNT_BY_USER_ID = "" + "SELECT map.name_uk, map.name_en, COUNT(*) AS count "
			+ "FROM map  INNER JOIN product_list ON product_list.mapk_id = map.id "
			+ "WHERE product_list.user_id = ? and product_list.date BETWEEN ? AND ? "
			+ "GROUP BY map.name_en ORDER BY count DESC;";

	private final String SELECT_TOTAL_COUNT_SHOP = "SELECT  COUNT(*) AS count " + "FROM  product_list WHERE product_list.date BETWEEN ? AND ? ";
	private final String SELECT_TOTAL_COUNT_SHOP_BY_USER_ID = "SELECT  COUNT(*) AS count " 
			+ "FROM  product_list wher user_id = ?  AND product_list.date BETWEEN ? AND ?";

	public List<DiagramShopCount> getShopCountAdmin(Date startDate, Date endDate)
			throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_SHOP_COUNT_ADMIN);
		statement.setDate(1, startDate);
		statement.setDate(2, endDate);
		ResultSet resultSet = statement.executeQuery();
		List<DiagramShopCount> shopCount = new Transformer<DiagramShopCount>(DiagramShopCount.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return shopCount;
	}

	public Integer getTotalShopCount(Date startDate, Date endDate) throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_TOTAL_COUNT_SHOP);
		statement.setDate(1, startDate);
		statement.setDate(2, endDate);
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		Integer count = resultSet.getInt("count");
		statement.close();
		return count;
	}

	public List<DiagramShopCount> getShopCountByUserId(Integer userId, Date startDate, Date endDate)
			throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_SHOP_COUNT_BY_USER_ID);
		statement.setInt(1, userId);
		statement.setDate(2, startDate);
		statement.setDate(3, endDate);
		ResultSet resultSet = statement.executeQuery();
		List<DiagramShopCount> shopCount = new Transformer<DiagramShopCount>(DiagramShopCount.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return shopCount;
	}
public int getTotalShopCountByUserId(Integer userId, Date startDate, Date endDate)
			throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_TOTAL_COUNT_SHOP_BY_USER_ID);
		statement.setInt(1, userId);
		statement.setDate(2, startDate);
		statement.setDate(3, endDate);
		ResultSet resultSet = statement.executeQuery();
		resultSet.next();
		Integer count = resultSet.getInt("count");
		statement.close();
		return count;
	}
}
