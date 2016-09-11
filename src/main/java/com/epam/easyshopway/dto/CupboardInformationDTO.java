package com.epam.easyshopway.dto;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.transformer.Transformer;
import com.epam.easyshopway.model.CupboardInformation;


public class CupboardInformationDTO extends SuperDTO{
	
	private final String GET_CUPBOARDS = "SELECT p.map_id,  cp.cupboard_id,  p.place, c.description_en, c.description_uk, "
			+ "c.board_amount FROM placement p JOIN cupboard_placement cp ON p.id = cp.placement_id JOIN cupboard "
			+ "c ON cp.cupboard_id = c.id WHERE p.map_id = ? ORDER BY cp.cupboard_id ASC, p.place ASC;";
	
	public List<CupboardInformation> getCupboardsByMapId(Integer id) throws SQLException, IllegalAccessException, InstantiationException {
		PreparedStatement statement = connection.prepareStatement(GET_CUPBOARDS);
		statement.setInt(1, id);
		ResultSet resultSet = statement.executeQuery();
		List<CupboardInformation> cupboardInformations = new Transformer<CupboardInformation>(CupboardInformation.class).fromRStoCollection(resultSet);
		statement.close();
		return cupboardInformations;
	}
}
