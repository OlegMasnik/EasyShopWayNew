package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Paydesk;

public class PaydeskDAO extends AbstractDAO<Paydesk> {
	private final String SELECT_ALL = "SELECT * FROM paydesk;";
	private final String SELECT_BY_ID = "SELECT * FROM paydesk WHERE id = ?";
	private final String INSERT = "INSERT INTO paydesk (map_id, place) VALUES ( ?, ?);";
	private final String UPDATE = "UPDATE paydesk SET map_id = ?place = ? where id =? ";
	private final String DELETE = "DELETE From paydesk where id = ?";

	@Override
	public int insert(Paydesk el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setInt(1, el.getMapId());
		statement.setInt(2, el.getPlace());
		return statement.executeUpdate();
	}

	@Override
	public int update(Integer id, Paydesk el) throws SQLException {
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
	public List<Paydesk> getAll() throws SQLException, IllegalAccessException,
			InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = statement.executeQuery(SELECT_ALL);
		List<Paydesk> paydesks = new Transformer<Paydesk>(Paydesk.class)
				.fromRStoCollection(resultSet);
		statement.close();
		return paydesks;
	}

	@Override
	public Paydesk getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<Paydesk> paydesks = new Transformer<Paydesk>(Paydesk.class)
				.fromRStoCollection(resultSet);
		statement.close();
		if (paydesks.size() > 0)
			return paydesks.iterator().next();
		else
			return null;
	}
}
