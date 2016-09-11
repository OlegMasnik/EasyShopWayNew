package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductList;

public class ProductListDAO extends AbstractDAO<ProductList> {
	Transformer<ProductList> transformer = new Transformer<>(ProductList.class);
	private final String ADD_PRODUCT_LIST = "INSERT INTO product_list (user_id, date, time, map_id) VALUES (?, CURDATE(), CURTIME(),?)";
	private final String DELETE_PRODUCT_LIST_BY_INDEX = "DELETE FROM product_list WHERE id=?";
	private final String UPDATE_PRODUCT_LIST_BY_INDEX = "UPDATE product_list SET user_id=?,  date=?, time=?, map_id = ? WHERE id=?";
	private final String GET_PRODUCT_LIST_BY_INDEX = "SELECT * FROM product_list WHERE id=?";
	private final String GET_ALL_PRODUCT_LISTS = "SELECT * FROM product_list";
	private final String GET_CURRENT_PRODUCT_LISTS_ID = "SELECT id FROM product_list where user_id=? ORDER BY id DESC LIMIT 1";

	public ProductListDAO() {
		super();
	}

	@Override
	public int insert(ProductList productList) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(ADD_PRODUCT_LIST);
		statement.setInt(1, productList.getUserId());
		statement.setInt(2, productList.getMapId());

		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	public int insertAndGetId(ProductList productList) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(ADD_PRODUCT_LIST);
		statement.setInt(1, productList.getUserId());
		statement.setInt(2, productList.getMapId());
		statement.executeUpdate();
		statement.close();
		PreparedStatement st = connection
				.prepareStatement(GET_CURRENT_PRODUCT_LISTS_ID);
		st.setInt(1, productList.getUserId());
		ResultSet result = st.executeQuery();
		if (result.next()) {
			Integer idList = result.getInt("id");
			st.close();
			return idList;
		} else {
			st.close();
			return -1;
		}
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
