package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.ProductListDAO;
import com.epam.easyshopway.model.ProductList;
import com.epam.easyshopway.model.ProductsAndList;

public class ProductListService {
	public int insert(ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.insert(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public int insertAndGetId(ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.insertAndGetId(productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	public void insertListAndProduct(ProductList productList, List<Integer> productIds) {
		Integer id = insertAndGetId(productList);
		ProductsAndList productsAndList;
		for(Integer idproduct: productIds){
			productsAndList = new ProductsAndList(id, idproduct);
			ProductsAndListService.insert(productsAndList);
		}
	}

	public int delete(Integer index) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.delete(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public ProductList getById(Integer index) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.getById(index);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<ProductList> getAll() {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public int update(Integer index, ProductList productList) {
		try (ProductListDAO productDAO = new ProductListDAO()) {
			return productDAO.update(index, productList);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	// //add handle of reflection exceptions
	// public static int insert(Product product) {
	// try (ProductDAO productDAO = new ProductDAO()) {
	// return productDAO.insert(product);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }
	//
	// public static int delete(int index) {
	// try (ProductDAO productDAO = new ProductDAO()) {
	// return productDAO.delete(index);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }
	//
	// public static Product getById(int index) {
	// try (ProductDAO productDAO = new ProductDAO()) {
	// return productDAO.getById(index);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// public static List<Product> getAll() {
	// try (ProductDAO productDAO = new ProductDAO()) {
	// return productDAO.getAll();
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return null;
	// }
	//
	// public static int update(Integer id, Product product) {
	// try (ProductDAO productDAO = new ProductDAO()) {
	// return productDAO.update(id, product);
	// } catch (SQLException e) {
	// e.printStackTrace();
	// } catch (Exception e) {
	// e.printStackTrace();
	// }
	// return 0;
	// }
}
