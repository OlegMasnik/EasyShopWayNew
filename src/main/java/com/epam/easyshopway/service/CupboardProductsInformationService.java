package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dto.CupboardProductsInformationDTO;
import com.epam.easyshopway.model.CupboardProductInformation;

public class CupboardProductsInformationService {
	private static List<CupboardProductsInformationDTO> getAllProductbyCupdoardId(
			Integer id) {
		List<CupboardProductsInformationDTO> productInformations = null;
		try (CupboardProductsInformationDTO dto = new CupboardProductsInformationDTO()) {
			productInformations = (List<CupboardProductsInformationDTO>) dto.getAllProductbyCupdoardId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return productInformations;
		}
	}

}
