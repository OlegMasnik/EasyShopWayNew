package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Map;

public class MapDAO extends AbstractDAO<Map> {
	private final String INSERT_MAP = "INSERT INTO map (weight, height, active) VALUES (?, ?, ?);";
	private final String DELETE_MAP_BY_ID = "UPDATE map SET active=0 WHERE id = ?;";
	private final String SELECT_MAP_BY_ID = "SELECT * FROM map WHERE id = ?;";
	private final String SELECT_ALL_MAPS = "SELECT * FROM map WHERE active=1;";
	private final String UPDATE_MAP_BY_ID = "UPDATE map SET weight = ?, height = ?, active = ? WHERE id = ?;";

	public MapDAO() {
		super();
	}

	@Override
	public int insert(Map map) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT_MAP);
		statement.setInt(1, map.getWeight());
		statement.setInt(2, map.getHeight());
		statement.setBoolean(3, map.isActive());
		int result = statement.executeUpdate();
		return result;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_MAP_BY_ID);
		statement.setInt(1, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public Map getById(Integer id) throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_MAP_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Map> maps = new Transformer<Map>(Map.class).fromRStoCollection(resultSet);
		statement.close();
		if (maps.size() > 0) {
			return maps.iterator().next();
		} else {
			return null;
		}

	}

	@Override
	public List<Map> getAll() throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL_MAPS);
		ResultSet resultSet = statement.executeQuery();
		List<Map> maps = new Transformer<Map>(Map.class).fromRStoCollection(resultSet);
		statement.close();
		return maps;

	}

	@Override
	public int update(Integer mapId, Map map) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_MAP_BY_ID);
		statement.setInt(1, map.getWeight());
		statement.setInt(2, map.getHeight());
		statement.setBoolean(3, map.isActive());
		statement.setInt(4, mapId);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}
}
