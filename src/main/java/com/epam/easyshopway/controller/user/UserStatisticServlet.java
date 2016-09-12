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
import com.epam.easyshopway.model.DiagramShopCount;
import com.epam.easyshopway.model.ProductsTypeCount;
import com.epam.easyshopway.service.DiagramShopCountService;
import com.epam.easyshopway.service.ProductsTypeCountService;
import com.sun.org.apache.bcel.internal.generic.NEW;

import jdk.nashorn.internal.scripts.JS;

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
		List<DiagramShopCount> shopCounts;
		int count;
		if ("user".equals(user.getRole())){
			productTypes = ProductsTypeCountService.getUserProductTypesUser(user.getId(), startDate, endDate);
			shopCounts = DiagramShopCountService.getShopCountByUserId(user.getId(), startDate, endDate);
			count = DiagramShopCountService.getTotalShopCountByUserId(user.getId(), startDate, endDate);
			System.out.println(count);
		}else {
			productTypes = ProductsTypeCountService.getUserProductTypesAdmin(startDate, endDate);
			shopCounts = DiagramShopCountService.getShopCount(startDate, endDate);
			count = DiagramShopCountService.getTotalShopCount(startDate, endDate);
			System.out.println(count);
		}
		boolean isEnglish = "en".equals(user.getLanguage());
		JSONObject pieChart = drawPieChart(productTypes, startDate, endDate, isEnglish);	
		JSONObject columnChart = drawColumnChart(shopCounts, startDate, endDate, isEnglish, count);
		System.out.println(pieChart);
		System.out.println(columnChart);
		JSONObject responseObject = new JSONObject();
		responseObject.put("pie", pieChart);
		responseObject.put("column", columnChart);
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
		title.put("text", isEnglish ? "Often searched groups of food" : "����� ��������, �� ����� �������");
		responseObject.put("title", title);
		inSeries.put("name", isEnglish ? "Persentage" : "� ��������");
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
	
	@SuppressWarnings("unchecked")
	public JSONObject drawColumnChart (List<DiagramShopCount> shopsCount, Date startDate, Date endDate, boolean isEnglish, int count){
		JSONObject responseObject = new JSONObject();
		JSONArray series = new JSONArray();
		JSONObject inSeries = new JSONObject();
		JSONObject xAxis = new JSONObject();
		xAxis.put("type", isEnglish ? "Shops" : "��������");
		responseObject.put("xAxis", xAxis);
		JSONObject yAxis = new JSONObject();
		JSONObject titleAxis = new JSONObject();
		titleAxis.put("text", isEnglish ? "Popularity" : "�����������");
		yAxis.put("title", titleAxis);
		responseObject.put("yAxis", yAxis);
		JSONArray data = new JSONArray();
		inSeries.put("colorByPoint", true);
		JSONObject title = new JSONObject();
		title.put("text", isEnglish ? "Most popular shops" : "������������� ��������");
		responseObject.put("title", title);
		inSeries.put("name", isEnglish ? "Shop" : "�������");
		for (int i=0; i<shopsCount.size(); i++){
			JSONObject shop = new JSONObject();
			String shopName = isEnglish ? shopsCount.get(i).getNameEnglish() : shopsCount.get(i).getNameEnglish() ;
			shop.put("name", shopName);
System.out.println(shopsCount.get(i).getCount());
			double percent = (shopsCount.get(i).getCount() / (double)count) * 100;
			shop.put("y", percent);
			data.add(shop);
		}
		inSeries.put("data", data);
		series.add(inSeries);
		responseObject.put("series", series);
		return responseObject;
	}
}
