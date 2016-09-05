package com.epam.easyshopway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductList;
import com.epam.easyshopway.model.ProductsAndList;

public class ProductsAndListDAO extends AbstractDAO<ProductsAndList> {
	private Connection connection;
	private Transformer<ProductsAndList> transformer;
	private final String ADD_PRODUCT_TO_LIST = "INSERT INTO list_and_product (product_id, product_list_id) VALUES (?, ?)";
	private final String DELETE_list_and_product_BY_INDEX = "DELETE FROM list_and_product WHERE id=?";
	private final String UPDATE_list_and_product_BY_INDEX = "UPDATE list_and_product SET product_id=?,  product_list_id=? WHERE id=?";
	private final String GET_list_and_product_BY_INDEX = "SELECT * FROM list_and_product WHERE id=?";
	private final String GET_ALL_list_and_productS = "SELECT * FROM list_and_product";

	public ProductsAndListDAO() {
		super();
	}

	@Override
	public int insert(ProductsAndList productsAndList) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(ADD_PRODUCT_TO_LIST);
		statement.setInt(1, productsAndList.getProductId());
		statement.setInt(2, productsAndList.getProductListId());

		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int update(Integer id, ProductsAndList productsAndList)
			throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(UPDATE_list_and_product_BY_INDEX);
		statement.setInt(1, productsAndList.getProductId());
		statement.setInt(2, productsAndList.getProductListId());
		statement.setInt(3, id);

		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(DELETE_list_and_product_BY_INDEX);
		statement.setInt(1, id);
		return statement.executeUpdate();
	}

	@Override
	public List<ProductsAndList> getAll() throws SQLException,
			IllegalAccessException, InstantiationException {
		transformer = new Transformer<>(ProductsAndList.class);
		List<ProductsAndList> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_ALL_list_and_productS);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;

	}

	@Override
	public ProductsAndList getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		transformer = new Transformer<>(ProductsAndList.class);
		List<ProductsAndList> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_list_and_product_BY_INDEX);
		statement.setInt(1, id);
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
}
