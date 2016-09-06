package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dto.FullProductListDTO;
import com.epam.easyshopway.model.FullProductList;

public class FullProductListService {
		
	public static List<FullProductList> getProductListByUserId(Integer id) {
		List<FullProductList> fullProductLists = null;
		try (FullProductListDTO dto = new FullProductListDTO()) {
			fullProductLists = dto.getProductListByUserId(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			return fullProductLists;
		}
	}
}
