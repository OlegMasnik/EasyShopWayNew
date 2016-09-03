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
	private final String DELETE_CUPBOARD_BY_ID = "UPDATE cupboard SET active=0 WHERE id=?";
	private final String UPDATE_CUPBOARD_BY_ID = "UPDATE cupboard SET board_amount=?, description_en=?, description_uk=?, active=? WHERE id=?";
	private final String GET_CUPBOARD_BY_ID = "SELECT * FROM cupboard WHERE id=?";
	private final String GET_ALL_CUPBOARD = "SELECT * FROM cupboard WHERE active=1";

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

}
