package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.ProductPlacement;

public class ProductPlacementDAO extends AbstractDAO<ProductPlacement> {
	private Transformer<ProductPlacement> transformer;
	private final String ADD_PRODUCT_PLACEMENT = "INSERT INTO product_placement "
			+ "(cupboard_id, product_id, place) VALUES (?, ?, ?)";
	private final String UPDATE_PRODUCT_PLACEMENT_BY_INDEX = "UPDATE product_placement SET cupboard_id=?, product_id = ?, place = ? WHERE id=?";
	private final String GET_PRODUCT_PLACEMENT_BY_INDEX = "SELECT * FROM product_placement WHERE id=?";
	private final String GET_ALL_PRODUCT_PLACEMENTS = "SELECT * FROM product_placement WHERE active=1";
	private final String GET_PRODUCT_PLACEMENY_BY_PRODUCT_ID = "select product_placement.* from product_placement join product on product.id = product_placement.product_id where product.id like ? and product.active = 1";

	public ProductPlacementDAO() {
		super();
	}

	@Override
	public int insert(ProductPlacement productPlacement) throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(ADD_PRODUCT_PLACEMENT);
		statement.setInt(1, productPlacement.getCupboardId());
		statement.setInt(2, productPlacement.getProductId());
		statement.setInt(3, productPlacement.getPlace());
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Deprecated
	public int delete(Integer index) throws SQLException {

		return 0;
	}

	@Override
	public ProductPlacement getById(Integer index) throws SQLException,
			InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductPlacement.class);
		List<ProductPlacement> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_PRODUCT_PLACEMENT_BY_INDEX);
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

	public List<ProductPlacement> getByName(Integer id) throws SQLException,
			InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductPlacement.class);
		List<ProductPlacement> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_PRODUCT_PLACEMENY_BY_PRODUCT_ID);
		statement.setInt(1, id);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	@Override
	public List<ProductPlacement> getAll() throws SQLException,
			InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(ProductPlacement.class);
		List<ProductPlacement> list = new ArrayList<>();
		PreparedStatement statement = connection
				.prepareStatement(GET_ALL_PRODUCT_PLACEMENTS);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	@Override
	public int update(Integer index, ProductPlacement productPlacement)
			throws SQLException {
		PreparedStatement statement = connection
				.prepareStatement(UPDATE_PRODUCT_PLACEMENT_BY_INDEX);
		statement.setInt(1, productPlacement.getCupboardId());
		statement.setInt(2, productPlacement.getProductId());
		statement.setInt(3, productPlacement.getPlace());
		statement.setInt(4, index);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}
}
