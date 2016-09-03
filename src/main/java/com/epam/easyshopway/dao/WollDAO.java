package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Woll;

public class WollDAO extends AbstractDAO<Woll> {
	private final String SELECT_ALL = "SELECT * FROM woll;";
	private final String SELECT_BY_ID = "SELECT * FROM woll WHERE id = ?";
	private final String INSERT = "INSERT INTO woll (map_id, place) VALUES ( ?, ?);";
	private final String UPDATE = "UPDATE woll SET map_id = ?place = ? where id =? ";
	private final String DELETE = "DELETE From woll where id = ?";

	@Override
	public int insert(Woll el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		return statement.executeUpdate();
	}

	@Override
	public int update(Integer id, Woll el) throws SQLException {
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
	public List<Woll> getAll() throws SQLException, IllegalAccessException,
			InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = statement.executeQuery(SELECT_ALL);
		List<Woll> wolls = new Transformer<Woll>(Woll.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return wolls;
	}

	@Override
	public Woll getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Woll> wolls = new Transformer<Woll>(Woll.class)
				.fromRStoCollection(resultSet);
		statement.close();
		if (wolls.size() > 0)
			return wolls.iterator().next();
		else
			return null;
	}

}
