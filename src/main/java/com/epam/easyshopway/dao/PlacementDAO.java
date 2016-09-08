package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Placement;

public class PlacementDAO extends AbstractDAO<Placement> {
	private final String SELECT_ALL = "SELECT * FROM placement;";
	private final String SELECT_BY_ID = "SELECT * FROM placement WHERE id = ?";
	private final String SELECT_BY_MAP_ID = "SELECT * FROM placement WHERE map_id = ?";
	private final String INSERT = "INSERT INTO placement (map_id, place, type) VALUES ( ?, ?,?);";
	private final String UPDATE = "UPDATE placement SET map_id = ?, place = ?, type=? where id =? ";
	private final String DELETE = "DELETE From placement where id = ?";
	private final String GET_PLACEMENT_BY_CUPBOARD_ID = "SELECT placement.place, placement.id, "
			+ "placement.type, placement.map_id FROM cupboard_placement "
			+ "INNER JOIN placement ON cupboard_placement.placement_id = placement.id"
			+ " WHERE cupboard_placement.cupboard_id = ? ORDER BY placement.place";

	@Override
	public int insert(Placement el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		statement.setString(3, el.getType());
		return statement.executeUpdate();
	}

	@Override
	public int update(Integer id, Placement el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		statement.setString(3, el.getType());
		statement.setInt(4, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE);
		statement.setInt(1, id);
		return statement.executeUpdate();
	}

	@Override
	public List<Placement> getAll() throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = statement.executeQuery(SELECT_ALL);
		List<Placement> placements = new Transformer<Placement>(Placement.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return placements;
	}

	public List<Placement> getcCupboardPlacement(Integer cupboard_id)
			throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection
				.prepareStatement(GET_PLACEMENT_BY_CUPBOARD_ID);
		statement.setInt(1, cupboard_id);
		ResultSet resultSet = statement.executeQuery();
		List<Placement> placements = new Transformer<Placement>(Placement.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return placements;
	}

	@Override
	public Placement getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Placement> placements = new Transformer<Placement>(Placement.class)
				.fromRStoCollection(resultSet);
		statement.close();
		if (placements.size() > 0)
			return placements.iterator().next();
		else
			return null;
	}

	public List<Placement> getcPlacementByMapId(Integer id)
			throws SQLException, InstantiationException, IllegalAccessException {
		PreparedStatement statement = connection
				.prepareStatement(SELECT_BY_MAP_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Placement> placements = new Transformer<Placement>(Placement.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return placements;
	}
}
