package com.epam.easyshopway.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.Cupboard;

public class CupbordDAO extends AbstractDAO<Cupboard> {
	private Transformer<Cupboard> transformer;

	private final String ADD_CUPBOARD = "INSERT INTO cupboard(board_amount, description_en, description_uk, active) VALUES (?, ?, ?, ?)";
	private final String DELETE_CUPBOARD_BY_ID = "DELETE FROM cupboard WHERE id = ?";
	private final String UPDATE_CUPBOARD_BY_ID = "UPDATE cupboard SET board_amount=?, description_en=?, description_uk=?, active=? WHERE id=?";
	private final String GET_CUPBOARD_BY_ID = "SELECT * FROM cupboard WHERE id=?";
	private final String GET_ALL_CUPBOARD = "SELECT * FROM cupboard WHERE active=1";
	private final String GET_LAST_INSERTED = "SELECT * FROM cupboard c WHERE c.id IN (SELECT MAX(id) FROM cupboard c1);";
	private final String GET_CUPBOARD_BY_MAP_ID = "SELECT c.id, c.description_en, c.description_uk, c.board_amount FROM cupboard c JOIN cupboard_placement cp ON c.id = cp.cupboard_id JOIN placement p ON cp.placement_id = p.id WHERE p.map_id = ? GROUP BY c.id;";

	public CupbordDAO() {
		super();
	}

	@Override
	public int insert(Cupboard cupboard) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(ADD_CUPBOARD);
		statement.setInt(1, cupboard.getBoardAmount());
		statement.setString(2, cupboard.getDescriptionEn());
		statement.setString(3, cupboard.getDescriptionUk());
		statement.setInt(4, 1);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int update(Integer id, Cupboard cupboard) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(UPDATE_CUPBOARD_BY_ID);
		statement.setInt(1, cupboard.getBoardAmount());
		statement.setString(2, cupboard.getDescriptionEn());
		statement.setString(3, cupboard.getDescriptionUk());
		statement.setInt(4, 1);
		statement.setInt(5, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public int delete(Integer id) throws SQLException {
		PreparedStatement statement = connection.prepareStatement(DELETE_CUPBOARD_BY_ID);
		statement.setInt(1, id);
		int result = statement.executeUpdate();
		statement.close();
		return result;
	}

	@Override
	public List<Cupboard> getAll() throws SQLException, IllegalAccessException, InstantiationException {
		transformer = new Transformer<>(Cupboard.class);
		List<Cupboard> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_ALL_CUPBOARD);
		ResultSet rs = statement.executeQuery();
		list = transformer.fromRStoCollection(rs);
		rs.close();
		statement.close();
		return list;
	}

	@Override
	public Cupboard getById(Integer id) throws SQLException, IllegalAccessException, InstantiationException {
		transformer = new Transformer<>(Cupboard.class);
		List<Cupboard> list = new ArrayList<>();
		PreparedStatement statement = connection.prepareStatement(GET_CUPBOARD_BY_ID);
		statement.setInt(1, id);
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
	
	public Cupboard getLastInserted() throws SQLException, InstantiationException, IllegalAccessException{
		PreparedStatement statement = connection.prepareStatement(GET_LAST_INSERTED);
		ResultSet resultSet = statement.executeQuery();
		List<Cupboard> cupboards = new Transformer<Cupboard>(Cupboard.class).fromRStoCollection(resultSet);
		statement.close();
		return cupboards == null ? null : cupboards.iterator().next();
	}
	
	public List<Cupboard> getByMapId(Integer mapId) throws SQLException, InstantiationException, IllegalAccessException{
		PreparedStatement statement = connection.prepareStatement(GET_CUPBOARD_BY_MAP_ID);
		statement.setInt(1, mapId);
		ResultSet resultSet = statement.executeQuery();
		List<Cupboard> cupboards = new Transformer<Cupboard>(Cupboard.class).fromRStoCollection(resultSet);
		statement.close();
		return cupboards;
	}

}
