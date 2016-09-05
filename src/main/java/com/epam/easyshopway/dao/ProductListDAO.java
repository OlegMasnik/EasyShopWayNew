package com.epam.easyshopway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductList;

public class ProductListDAO extends AbstractDAO<ProductList> {
	private Connection connection;
	private Transformer<ProductList> transformer;
	private final String ADD_PRODUCT_LIST = "INSERT INTO product_list (user_id, date, time) VALUES (?, ?, ?)";
	private final String DELETE_PRODUCT_LIST_BY_INDEX = "DELETE FROM product_list WHERE id=?";
	private final String UPDATE_PRODUCT_LIST_BY_INDEX = "UPDATE product_list SET user_id=?,  date=?, time=? WHERE id=?";
	private final String GET_PRODUCT_LIST_BY_INDEX = "SELECT * FROM product_list WHERE id=?";
	private final String GET_ALL_PRODUCT_LISTS = "SELECT * FROM product_list";

	public ProductListDAO() {
		super();
	}

	@Override
	public int insert(ProductList productList) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(ADD_PRODUCT_LIST);
		statement.setInt(1, productList.getUserId());
		statement.setDate(2, productList.getDate());
		statement.setTime(3, productList.getTime());
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int delete(Integer index) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(DELETE_PRODUCT_LIST_BY_INDEX);
		statement.setInt(1, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public ProductList getById(Integer index) throws SQLException,
			InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductList.class);
		List<ProductList> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_PRODUCT_LIST_BY_INDEX);
		statement.setInt(1, index);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		if (list.size() > 0) {
			return list.iterator().next();
		} else {
			return null;
		}
	}

	@Override
	public List<ProductList> getAll() throws SQLException,
			InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductList.class);
		List<ProductList> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_ALL_PRODUCT_LISTS);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	@Override
	public int update(Integer index, ProductList productList)
			throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(UPDATE_PRODUCT_LIST_BY_INDEX);
		statement.setInt(1, productList.getUserId());
		statement.setDate(2, productList.getDate());
		statement.setTime(3, productList.getTime());
		statement.setInt(4, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}
}
