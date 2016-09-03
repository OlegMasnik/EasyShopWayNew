package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductPlacementDAO;
import com.epam.easyshopway.model.ProductPlacement;

public class ProductPlacementServic {
	public int insert(ProductPlacement productList) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ProductPlacement getById(Integer index) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProductPlacement> getAll() {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Integer index, ProductPlacement productList) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.update(index, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
