package com.epam.easyshopway.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.connection.ConnectionManager;
import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.dto.Coordinates;

public class CoordinatesDAO implements AutoCloseable{
	private final String GET_CUPBOARD_COORDINATES_UA = "select cupboard_placement.x_start, cupboard_placement.x_end, cupboard_placement.y_start, cupboard_placement.y_end from cupboard_placement"
	 + "join cupboard on cupboard_placement.cupboard_id = cupboard.id"
		+ "join product_placement on cupboard.id = product_placement.cupboard_id"
			+ "join product on product_placement.product_id = product.id"
            + "where product.name_ua like ? and product.active = 1";
	private final String GET_PRODUCT_COORDINATES_UA = "select x_start, x_end, y_start, y_end from product_placement "
			+ "join product on product.id = product_placement.product_id where product.name_uk like ? and product.active=1";
	private final String GET_CUPBOARD_COORDINATES_EN = "select cupboard_placement.x_start, cupboard_placement.x_end, cupboard_placement.y_start, cupboard_placement.y_end from cupboard_placement"
			 + "join cupboard on cupboard_placement.cupboard_id = cupboard.id"
				+ "join product_placement on cupboard.id = product_placement.cupboard_id"
					+ "join product on product_placement.product_id = product.id"
		            + "where product.name_en like ? and product.active = 1";
	private final String GET_PRODUCT_COORDINATES_EN = "select x_start, x_end, y_start, y_end from product_placement "
			+ "join product on product.id = product_placement.product_id where product.name_en like ? and product.active=1";
	private Transformer<Coordinates> transformer;
	private Connection connection;
	
	public CoordinatesDAO() {
		super();
		connection = ConnectionManager.getInstance().getConnectionPool().getConnection();
	}
	
	public Coordinates getCoordinates(String productName, String language) throws SQLException, InstantiationException, IllegalAccessException {
		transformer = new Transformer<>(Coordinates.class);
		PreparedStatement ps;
		PreparedStatement psCB;
		if ("uk".equals(language)) {
			ps = connection.prepareStatement(GET_CUPBOARD_COORDINATES_UA);//отримання коорднатів стелажу з продуктом
			psCB = connection.prepareStatement(GET_PRODUCT_COORDINATES_UA);//отримання координатів продукту в стелажі
		} else {
			ps = connection.prepareStatement(GET_CUPBOARD_COORDINATES_EN);
			psCB = connection.prepareStatement(GET_PRODUCT_COORDINATES_EN);
		}
		ps.setString(1, productName);
		ResultSet rs = ps.executeQuery();
		List<Coordinates> cupboardCoordinatesList = transformer.fromRStoCollection(rs);
		Coordinates cupboardCoordinates;//cupboard with product on map coordinates
		if (cupboardCoordinatesList.size() > 0) {
			cupboardCoordinates = cupboardCoordinatesList.iterator().next();
		} else {
			return null;
		}
		psCB.setString(1, productName);
		ResultSet rsCB = psCB.executeQuery();
		List<Coordinates> productOnCupboardCoordinatesList = transformer.fromRStoCollection(rsCB);
		Coordinates productOnCupboardCoordinates;
		if (productOnCupboardCoordinatesList.size() > 0) {
			productOnCupboardCoordinates = productOnCupboardCoordinatesList.iterator().next();
		} else {
			return null;
		}
		int start = productOnCupboardCoordinates.getyStart();
		int end = productOnCupboardCoordinates.getyEnd();
		if (cupboardCoordinates.getxStart() == cupboardCoordinates.getxEnd()) {
			int yStart = cupboardCoordinates.getyStart();
			cupboardCoordinates.setyStart(yStart + start);
			cupboardCoordinates.setyEnd(yStart + end);
		} else if (cupboardCoordinates.getyStart() == cupboardCoordinates.getyEnd()) {
			int xStart = cupboardCoordinates.getxStart();
			cupboardCoordinates.setxStart(xStart + start);
			cupboardCoordinates.setxEnd(xStart + end);
		}
		return cupboardCoordinates;
	}
	
	public static void main(String[] args) {
		System.out.println("asd");
	}

	@Override
	public void close() throws Exception {
		ConnectionManager.getInstance().getConnectionPool().putConnection(connection);	
	}
}
