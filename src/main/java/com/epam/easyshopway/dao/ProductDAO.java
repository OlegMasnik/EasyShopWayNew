
package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Product;

public class ProductDAO extends AbstractDAO<Product> {
	private Transformer<Product> transformer;
	private final String ADD_PRODUCT = "INSERT INTO product(product_type_id, name_uk, name_en, active) VALUES (?, ?, ?, ?)";
	private final String DELETE_PRODUCT_BY_INDEX = "UPDATE product set active=0 WHERE id=?";
	private final String UPDATE_PRODUCT_BY_INDEX = "UPDATE product SET product_type_id=?, name_uk=?, name_en=?, active=? WHERE id=?";
	private final String GET_PRODUCT_BY_INDEX = "SELECT * FROM product WHERE id=?";
	private final String GET_ALL_PRODUCTS = "SELECT * FROM product WHERE active=1";

	public ProductDAO() {
		super();
	}

	@Override
	public int insert(Product product) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT);
		statement.setInt(1, product.getProductTypeId());
		statement.setString(2, product.getNameUk());
		statement.setString(3, product.getNameEn());
		statement.setInt(4, 1);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int delete(Integer index) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_BY_INDEX);
		statement.setInt(1, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public Product getById(Integer index) throws SQLException, InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(Product.class);
		List<Product> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_BY_INDEX);
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
	public List<Product> getAll() throws SQLException, InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(Product.class);
		List<Product> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	@Override
	public int update(Integer id, Product product) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_BY_INDEX);
		statement.setInt(1, product.getProductTypeId());
		statement.setString(2, product.getNameUk());
		statement.setString(3, product.getNameEn());
		statement.setInt(4, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}
}
