package com.epam.easyshopway.controller;

import java.io.IOException;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epam.easyshopway.model.FullProductList;
import com.epam.easyshopway.model.ProductInformation;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.ProductInformationService;

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		String uri = request.getRequestURI();
		
		if (uri.endsWith("search")) {
			request.getRequestDispatcher("/WEB-INF/temp.jsp").forward(request, response);
		} else {
			
			List<ProductInformation> products = ProductInformationService.getAllProductOnSupermarket();
			
			JSONObject object = new JSONObject();
			object.put("products", setJsonArrayType(products));
			
			System.out.println(products.size());

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getAttribute("user");
		
		if (user != null) {
			
		}
	}

	private JSONArray setJsonArrayType(Collection<ProductInformation> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (ProductInformation p : list) {
			object = new JSONObject();
			object.put("img", p.getImage());
			object.put("name_uk", p.getProductNameUk());
			object.put("name_en", p.getProductNameEn());
			object.put("id", p.getId());
			
			JSONArray cordArr = new JSONArray();
			
			for (Integer cord : p.getCoordinates()) {
				cordArr.add(cord);
			}
			
			object.put("coordinates", cordArr);
			jsonArray.add(object);
		}
		return jsonArray;
	}
}
