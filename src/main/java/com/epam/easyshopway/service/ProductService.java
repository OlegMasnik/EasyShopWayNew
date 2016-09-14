package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductDAO;
import com.epam.easyshopway.dao.ProductTypeDAO;
import com.epam.easyshopway.model.Product;

public class ProductService {
	//add handle of reflection exceptions
	public static int insert(Product product) {
		try (ProductDAO productDAO = new ProductDAO()) {
			return productDAO.insert(product);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(int index) {
		try (ProductDAO productDAO = new ProductDAO()) {
			return productDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static Product getById(int index) {
		try (ProductDAO productDAO = new ProductDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<Product> getAll() {
		try (ProductDAO productDAO = new ProductDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer id, Product product) {
		try (ProductDAO productDAO = new ProductDAO()) {
			return productDAO.update(id, product);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	public static boolean hasNameEn (String nameEn){
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.hasNameEn(nameEn);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static boolean hasNameUk (String nameUk){
		try (ProductTypeDAO productDAO = new ProductTypeDAO()) {
			return productDAO.hasNameUk(nameUk);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
