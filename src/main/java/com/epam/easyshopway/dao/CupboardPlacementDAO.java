package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.CupboardPlacement;

public class CupboardPlacementDAO extends AbstractDAO<CupboardPlacement> {
	private final String SELECT_ALL = "SELECT * FROM cupboard_placement;";
	private final String SELECT_BY_ID = "SELECT * FROM cupboard_placement WHERE id = ?";
	private final String INSERT = "INSERT INTO cupboard_placement (cupboard_id, placement_id) VALUES ( ?, ?);";
	private final String UPDATE = "UPDATE cupboard_placement SET cupboard_id = ?, placement_id = ? where id =? ";

	@Override
	public int insert(CupboardPlacement el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(INSERT);
		statement.setInt(1, el.getCupboardId());
		statement.setInt(2, el.getPlacementId());
		return statement.executeUpdate();
	}

	@Override
	public int update(Integer id, CupboardPlacement el) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE);
		statement.setInt(1, el.getCupboardId());
		statement.setInt(2, el.getPlacementId());
		statement.setInt(3, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	@Deprecated
	public int delete(Integer id) throws SQLException {
		return 0;
	}

	@Override
	public List<CupboardPlacement> getAll() throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_ALL);
		ResultSet resultSet = statement.executeQuery(SELECT_ALL);
		List<CupboardPlacement> placements = new Transformer<CupboardPlacement>(
				CupboardPlacement.class).fromRStoCollection(resultSet);
		statement.close();
		return placements;
	}

	@Override
	public CupboardPlacement getById(Integer id) throws SQLException,
			IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(SELECT_BY_ID);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<CupboardPlacement> cupboardPlacements = new Transformer<CupboardPlacement>(
				CupboardPlacement.class).fromRStoCollection(resultSet);
		statement.close();
		if (cupboardPlacements.size() > 0)
			return cupboardPlacements.iterator().next();
		else
			return null;
	}
}
