package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dto.ProductInformationDTO;
import com.epam.easyshopway.model.ProductInformation;

public class ProductInformationService {
	public static List<ProductInformation> getAllProduct() {
		List<ProductInformation> productInformations = null;
		try (ProductInformationDTO dto = new ProductInformationDTO()) {
			productInformations = dto.getAllProduct();
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

	public static void main(String[] args) {
		List<ProductInformation> informations = getAllProduct();
		for (ProductInformation information : informations) {
			information.setCoordinates();
			System.out.println(information.getProductNameUk());
			System.out.println(information.getCoordinates().size());
		}
	}
}
