package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductListDAO;
import com.epam.easyshopway.model.ProductList;
import com.epam.easyshopway.model.ProductsAndList;

public class ProductListService {
	public static int insert(ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int insertAndGetId(ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.insertAndGetId(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static void insertListAndProduct(ProductList productList, List<Long> productIds) {
		Integer id = insertAndGetId(productList);
		ProductsAndList productsAndList;
		for (Long idproduct : productIds) {
			productsAndList = new ProductsAndList( (int) (long) idproduct, id);
			ProductsAndListService.insert(productsAndList);
		}
	}

	public static int delete(Integer index) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ProductList getById(Integer index) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ProductList> getAll() {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer index, ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.update(index, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

}