package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dto.CupboardProductsInformationDTO;
import com.epam.easyshopway.model.CupboardProductInformation;

public class CupboardProductsInformationService {

	public static void main(String[] args) {
		List<CupboardProductInformation> cup = getAllProductbyCupdoardId(1);
		
		System.out.println(cup.size());
	}

	private static List<CupboardProductInformation> getAllProductbyCupdoardId(Integer id) {
		List<CupboardProductInformation> productInformations = null;
		try (CupboardProductsInformationDTO dto = new CupboardProductsInformationDTO()) {
			productInformations = dto.getAllProductbyCupdoardId(id);
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
