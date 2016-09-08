package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductsAndListDAO;
import com.epam.easyshopway.model.ProductsAndList;

public class ProductsAndListService {
	public static int insert(ProductsAndList productList) {
		try (ProductsAndListDAO productsAndLisDAO = new ProductsAndListDAO()) {
			return productsAndLisDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Integer index) {
		try (ProductsAndListDAO productsAndLisDAO = new ProductsAndListDAO()) {
			return productsAndLisDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ProductsAndList getById(Integer index) {
		try (ProductsAndListDAO productsAndLisDAO = new ProductsAndListDAO()) {
			return productsAndLisDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ProductsAndList> getAll() {
		try (ProductsAndListDAO productsAndLisDAO = new ProductsAndListDAO()) {
			return productsAndLisDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer index, ProductsAndList productList) {
		try (ProductsAndListDAO productsAndLisDAO = new ProductsAndListDAO()) {
			return productsAndLisDAO.update(index, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}
