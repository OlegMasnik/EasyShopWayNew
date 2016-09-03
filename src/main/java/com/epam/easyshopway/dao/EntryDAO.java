package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Entry;

public class EntryDAO extends AbstractDAO<Entry> {
	private final String SELECT_ALL = "SELECT * FROM woll;";
	private final String SELECT_BY_ID = "SELECT * FROM entry WHERE id = ?";
	private final String INSERT = "INSERT INTO entry (map_id, place) VALUES ( ?, ?);";
	private final String UPDATE = "UPDATE entry SET map_id = ?place = ? where id =? ";
	private final String DELETE = "DELETE From entry where id = ?";

	@Override
	public int insert(Entry el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		return statement.executeUpdate();
	}

	@Override
	public int update(Integer id, Entry el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		statement.setInt(3, id);
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
	public List<Entry> getAll() throws SQLException, IllegalAccessException,
			InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = statement.executeQuery(SELECT_ALL);
		List<Entry> entrys = new Transformer<Entry>(Entry.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return entrys;
	}

	@Override
	public Entry getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Entry> entrys = new Transformer<Entry>(Entry.class)
				.fromRStoCollection(resultSet);
		statement.close();
		if (entrys.size() > 0)
			return entrys.iterator().next();
		else
			return null;
	}
}
