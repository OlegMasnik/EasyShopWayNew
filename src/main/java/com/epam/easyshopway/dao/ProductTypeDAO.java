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
	private final String CHECK_NAME_EN = "SELECT * FROM product_type pt WHERE pt.name_en LIKE ?;";
	private final String CHECK_NAME_UK = "SELECT * FROM product_type pt WHERE pt.name_uk LIKE ?;";

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
	
	public boolean hasNameEn (String nameEn) throws SQLException, InstantiationException, IllegalAccessException{
		PreparedStatement statement = connection.prepareStatement(CHECK_NAME_EN);
		statement.setString(1, nameEn);
		ResultSet resultSet = statement.executeQuery();
		List<ProductType> productTypes = new Transformer<ProductType>(ProductType.class).fromRStoCollection(resultSet);
		statement.close();
		return !productTypes.isEmpty();
	}
	
	public boolean hasNameUk (String nameUk) throws SQLException, InstantiationException, IllegalAccessException{
		PreparedStatement statement = connection.prepareStatement(CHECK_NAME_UK);
		statement.setString(1, nameUk);
		ResultSet resultSet = statement.executeQuery();
		List<ProductType> productTypes = new Transformer<ProductType>(ProductType.class).fromRStoCollection(resultSet);
		statement.close();
		return !productTypes.isEmpty();
	}
}