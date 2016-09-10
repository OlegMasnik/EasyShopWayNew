package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

import com.epam.easyshopway.dto.FullProductListDTO;
import com.epam.easyshopway.model.FullProductList;

public class FullProductListService {
	
	public static void main(String[] args) {
		getGroupedList(11);
	}
		
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
	
	public static LinkedHashMap<Integer, List<FullProductList>> getGroupedList (Integer id) { 
	    List<FullProductList> userProducts = new ArrayList<>(); 
	    LinkedHashMap<Integer, List<FullProductList>> productLists = new LinkedHashMap<>(); 
	     
	    userProducts = getProductListByUserId(id); 
	     
	    List<FullProductList> current; 
	    for (FullProductList product : userProducts) { 
	      if ((current = productLists.get(product.getId())) == null) { 
	        current = new ArrayList<>(); 
	        current.add(product); 
	        productLists.put(product.getId(), current); 
	      } else { 
	        current.add(product); 
	      } 
	    } 
	     
	    return productLists; 
	}
}
