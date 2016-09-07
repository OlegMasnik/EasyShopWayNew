package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.ArrayList;
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

	private static List<ProductInformation> getAllProductByUserListId(Integer id) {
		List<ProductInformation> productInformations = null;
		try (ProductInformationDTO dto = new ProductInformationDTO()) {
			productInformations = dto.getAllProductbyProductListId(id);
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

	private static List<ProductInformation> getAllProductbyCupdoardId(Integer id) {
		List<ProductInformation> productInformations = null;
		try (ProductInformationDTO dto = new ProductInformationDTO()) {
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

	// мій маленький костиль для знаходження всіх продуктів, що РОЗСТАВЛЕНІ вже
	// в магазині
	public static List<ProductInformation> getAllProductOnSupermarket() {
		List<ProductInformation> informations = getAllProduct();
		List<ProductInformation> currentInformations = new ArrayList<>();
		for (ProductInformation information : informations) {
			information.setCoordinates();
			if (!information.getCoordinates().isEmpty()) {
				currentInformations.add(information);
			}
		}
		return currentInformations;
	}

	public static List<ProductInformation> getCurrentAllProductbyProductListId(
			Integer productlistId) {
		List<ProductInformation> informations = getAllProductByUserListId(productlistId);
		List<ProductInformation> currentInformations = new ArrayList<>();
		for (ProductInformation information : informations) {
			information.setCoordinates();
			if (!information.getCoordinates().isEmpty()) {
				currentInformations.add(information);
			}
		}
		return currentInformations;
	}

}
