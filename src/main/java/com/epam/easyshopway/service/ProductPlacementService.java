package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductPlacementDAO;
import com.epam.easyshopway.model.ProductPlacement;

public class ProductPlacementService {
	public static int insert(ProductPlacement productList) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int updateByCuoboardIdAndPlace(ProductPlacement productList) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.updateByCuoboardIdAndPlace(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public static int deleteByCupboardId(ProductPlacement productList) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.deleteByCupboardId(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static ProductPlacement getById(Integer index) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ProductPlacement> getByProductId(Integer id) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getByProductId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ProductPlacement> getByProductIdAndCupboardId(Integer productId, Integer cupboarId) {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getByProductIdAndCupboardId(productId, cupboarId);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<ProductPlacement> getAll() {
		try (ProductPlacementDAO productDAO = new ProductPlacementDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer index, ProductPlacement productList) {
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
