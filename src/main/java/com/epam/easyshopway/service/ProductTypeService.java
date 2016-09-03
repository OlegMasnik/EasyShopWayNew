package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductTypeDAO;
import com.epam.easyshopway.model.ProductType;

public class ProductTypeService {
	public int insert(ProductType productList) {
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int delete(Integer index) {
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ProductType getById(Integer index) {
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProductType> getAll() {
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Integer index, ProductType productList) {
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.update(index, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
