package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;


import com.epam.easyshopway.model.Cupboard;
import com.epam.easyshopway.model.CupboardPlacement;
import com.epam.easyshopway.model.CupboardProductInformation;
import com.epam.easyshopway.model.Placement;
import com.epam.easyshopway.model.Product;
import com.epam.easyshopway.model.ProductPlacement;
import com.epam.easyshopway.service.CupboardPlacementService;
import com.epam.easyshopway.service.CupboardProductsInformationService;
import com.epam.easyshopway.service.CupboardService;
import com.epam.easyshopway.service.PlacementService;
import com.epam.easyshopway.service.ProductPlacementService;
import com.epam.easyshopway.service.ProductService;

/**
 * Servlet implementation class AdminProductServlet
 */
public class AdminProductServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminProductServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		
		switch (type) {
			case "getAllProducts":{
				List<Product> allProducts = ProductService.getAll();
				JSONArray resultArray = new JSONArray();
				for (Product cInformation : allProducts){
					JSONObject product = new JSONObject();
					product.put("prodId", cInformation.getId());
					product.put("name_en", cInformation.getNameEn());
					product.put("name_uk", cInformation.getNameUk());
					resultArray.add(product);
				}
				response.getWriter().write(resultArray.toString());
			}
				break;
				
			case "getCupboardsProducts":{
				Integer cupboardId = Integer.valueOf(request.getParameter("cupboardId"));
				List<CupboardProductInformation> productsOnCupboard = 
						CupboardProductsInformationService.getAllProductbyCupdoardId(cupboardId);
				JSONArray resultArray = new JSONArray();
				for (CupboardProductInformation cInformation : productsOnCupboard){
					JSONObject product = new JSONObject();
					product.put("prodId", cInformation.getId());
					product.put("name_en", cInformation.getProductNameEn());
					product.put("name_uk", cInformation.getProductNameUk());
					JSONArray places = new JSONArray();
					for (Integer i : cInformation.getCoordinatesOnCupboard()){
						places.add(i);
					}
					product.put("place", places);
					resultArray.add(product);
				}
				response.getWriter().write(resultArray.toString());
			}
			break;
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		String type = request.getParameter("type");
		
		switch (type) {
				
			case "setProducts":{
				String data = request.getParameter("data");
				try {
					JSONParser parser = new JSONParser();
					JSONArray products = (JSONArray) parser.parse(data.toString());
					System.out.println("Products size = " + products.size());
					for (int i = 0; i < products.size(); i++){
						JSONObject product  = (JSONObject)products.get(i);
						System.out.println(product);
						int prodId = ((Long) product.get("prodId")).intValue();
						int cupboardId = ((Long) product.get("cupboardId")).intValue();
						int place = ((Long) product.get("place")).intValue();
						ProductPlacement productPlacement = new ProductPlacement();
						productPlacement.setProductId(prodId);
						productPlacement.setCupboardId(cupboardId);
						productPlacement.setPlace(place);
						ProductPlacementService.deleteByCupboardId(productPlacement);
						ProductPlacementService.insert(productPlacement);
					}
				
				} catch (org.json.simple.parser.ParseException e) {
					e.printStackTrace();
				}	
			}
				break;
		}
	}
	
	
	
//	In this format !!!!!!!
//	READ:
//
//		[
//			{prodId: 1,
//			name_en: asdasd, 
//			name_uk: asda, 
//			place: [0, 1, 2]}, 
//			
//			{prodId: 2,
//			name_en: asdasd, 
//			name_uk: asda, 
//			place: [0, 1, 2]}, 
//			
//			{prodId: 3,
//			name_en: asdasd, 
//			name_uk: asda, 
//			place: [0, 1, 2]}
//		]	
//
//			
//		WRITE:
//
//		data:
//		[
//			{prodId: 1,
//			cupboardId: 1,
//			places: 1},
//
//			{prodId: 2,
//			cupboardId: 1,
//			places: 2}
//			
//		]
//	

	public static void main(String[] args) throws ParseException {
		JSONArray array = new JSONArray();
		JSONObject object = new JSONObject();
		object.put("prodId", 1);
		object.put("cupboardId", 1);
		object.put("place", 1);
		array.add(object);
		JSONParser parser = new JSONParser();
		JSONArray arr = (JSONArray) parser.parse(array.toString());
		JSONObject object2  = (JSONObject)arr.get(0);
		System.out.println("prodId " + object2.get("prodId") + " cupboardId " + object2.get("cupboardId") +  
				" place " + object2.get("place"));
	}

}
