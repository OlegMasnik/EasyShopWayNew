package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductType;

public class ProductTypeDAO extends AbstractDAO<ProductType>{
	private Transformer<ProductType> transformer;
	private final String ADD_PRODUCT_TYPE = "INSERT INTO product_type(name_uk, name_en, img, active) VALUES (?, ?, ?, ?)";
	private final String DELETE_PRODUCT_TYPE_BY_INDEX = "UPDATE product_type SET active=0 WHERE id=?";
	private final String UPDATE_PRODUCT_TYPET_BY_INDEX = "UPDATE product_type SET name_uk=?, name_en=?, img=?, active =? WHERE id=?";
	private final String GET_PRODUCT_TYPE_BY_INDEX = "SELECT * FROM product_type WHERE id=?";
	private final String GET_ALL_PRODUCTS_TYPES = "SELECT * FROM product_type WHERE active=1";

	public ProductTypeDAO() {
		super();
	}

	@Override
	public int insert(ProductType productType) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(ADD_PRODUCT_TYPE);
		statement.setString(1, productType.getNameUk());
		statement.setString(2, productType.getNameEn());
		statement.setString(3, productType.getImageUrl());
		statement.setInt(4, 1);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	public int delete(Integer index) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_PRODUCT_TYPE_BY_INDEX);
		statement.setInt(1, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	public ProductType getById(Integer index) throws SQLException, InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductType.class);
		List<ProductType> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_PRODUCT_TYPE_BY_INDEX);
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

	public List<ProductType> getAll() throws SQLException, InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductType.class);
		List<ProductType> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_ALL_PRODUCTS_TYPES);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	public int update(Integer index, ProductType productType) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_PRODUCT_TYPET_BY_INDEX);
		statement.setString(1, productType.getNameUk());
		statement.setString(2, productType.getNameEn());
		statement.setString(3, productType.getImageUrl());
		statement.setInt(4, 1);
		statement.setInt(5, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}
}