package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dto.CupboardInformationDTO;
import com.epam.easyshopway.model.CupboardInformation;

public class CupboardInformationService {
	public static List<CupboardInformation> getCupboardsByMapId(Integer id) {
		try (CupboardInformationDTO cupboardInformationDTO = new CupboardInformationDTO()) {
			return cupboardInformationDTO.getCupboardsByMapId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
