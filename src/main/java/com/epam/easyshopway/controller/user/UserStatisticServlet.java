package com.epam.easyshopway.controller.user;

import java.io.IOException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.model.ProductsTypeCount;
import com.epam.easyshopway.service.ProductsTypeCountService;
import com.sun.org.apache.bcel.internal.generic.NEW;

/**
 * Servlet implementation class UserStatisticServlet
 */
public class UserStatisticServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UserStatisticServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/user/statistic.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		User user = (User) request.getSession().getAttribute("user");
		Date startDate = Date.valueOf(request.getParameter("startDate"));
		Date endDate = Date.valueOf(request.getParameter("endDate"));
		
		List<ProductsTypeCount> productTypes;
		if ("user".equals(user.getRole()))
			productTypes = ProductsTypeCountService.getUserProductTypesUser(user.getId(), startDate, endDate);
		else 
			productTypes = ProductsTypeCountService.getUserProductTypesAdmin(user.getId(), startDate, endDate);
		JSONObject responseObject = drawPieChart(productTypes, startDate, endDate, "en".equals(user.getLanguage()));	
		response.getWriter().write(responseObject.toString());
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject drawPieChart (List<ProductsTypeCount> productsType, Date startDate, Date endDate, boolean isEnglish){
		JSONObject responseObject = new JSONObject();
		JSONArray series = new JSONArray();
		JSONObject inSeries = new JSONObject();
		JSONArray data = new JSONArray();
		inSeries.put("colorByPoint", true);
		JSONObject title = new JSONObject();
		title.put("text", isEnglish ? "Often searched groups of food" : "Групи продуктів, які часто шукають");
		responseObject.put("title", title);
		inSeries.put("name", isEnglish ? "Persentage" : "У відсотках");
		for (int i=0; i<productsType.size(); i++){
			JSONObject foodType = new JSONObject();
			String productTypeName = isEnglish ? productsType.get(i).getNameEnglish() : productsType.get(i).getNameEnglish() ;
			foodType.put("name", productTypeName);
			double percent = productsType.get(i).getCount();
			foodType.put("y", percent);
			if (i == 0){
				foodType.put("sliced", true);
				foodType.put("selected", true);
			}
			data.add(foodType);
		}
		inSeries.put("data", data);
		series.add(inSeries);
		responseObject.put("series", series);
		return responseObject;
	}
}
