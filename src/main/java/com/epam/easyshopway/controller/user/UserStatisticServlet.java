package com.epam.easyshopway.controller.user;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.sql.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;
import org.json.simple.JSONArray;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.model.DiagramShopCount;
import com.epam.easyshopway.model.ProductsTypeCount;
import com.epam.easyshopway.service.DiagramShopCountService;
import com.epam.easyshopway.service.ProductsTypeCountService;

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
			try{
				JSONObject pieChart = drawPieChart(user, startDate, endDate);	
				JSONObject columnChart = drawColumnChart(user, startDate, endDate);
				System.out.println(pieChart);
				System.out.println(columnChart);
				JSONObject responseObject = new JSONObject();
				responseObject.put("pie", pieChart);
				responseObject.put("column", columnChart);
				response.getWriter().write(responseObject.toString());
			}catch(UnsupportedEncodingException e){
				e.printStackTrace();
			}
	}
	
	@SuppressWarnings("unchecked")
	public JSONObject drawPieChart (User user, Date startDate, Date endDate) throws UnsupportedEncodingException{
		boolean isEnglish = "en".equals(user.getLanguage());
		List<ProductsTypeCount> productsType;
		String diagramName;
		
		if("user".equals(user.getRole())){
			int id = user.getId();
			productsType = ProductsTypeCountService.getUserProductTypesUser(id, startDate, endDate);
			diagramName = isEnglish ? "Often searched groups of food" : "Групи продуктів, які Ви часто шукали";
		}else{
			productsType = ProductsTypeCountService.getUserProductTypesAdmin(startDate, endDate);
			diagramName = isEnglish ? "Often searched groups of food" : "Групи продуктів, які часто шукають";
		}
		
		JSONObject responseObject = new JSONObject();
		JSONArray series = new JSONArray();
		JSONObject inSeries = new JSONObject();
		JSONArray data = new JSONArray();
		inSeries.put("colorByPoint", true);
		JSONObject title = new JSONObject();
		title.put("text", diagramName);
		responseObject.put("title", title);
		inSeries.put("name", isEnglish ? "Persentage" : "У відсотках");
		for (int i=0; i<productsType.size(); i++){
			JSONObject foodType = new JSONObject();
			String productTypeName = isEnglish ? productsType.get(i).getNameEnglish() : productsType.get(i).getNameUkrainian() ;
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
	public JSONObject drawColumnChart (User user, Date startDate, Date endDate) throws UnsupportedEncodingException{
		boolean isEnglish = "en".equals(user.getLanguage());
		List<DiagramShopCount> shopsCount;
		int count;
		String diagramName;
		if ("user".equals(user.getRole())){
			int id = user.getId();
			shopsCount = DiagramShopCountService.getShopCountByUserId(id, startDate, endDate);
			count = DiagramShopCountService.getTotalShopCountByUserId(id, startDate, endDate);
			diagramName = isEnglish ? "Shops You often searched in" : "Магазини, в яких Ви часто шукаєте";
		}else {
			shopsCount = DiagramShopCountService.getShopCount(startDate, endDate);
			count = DiagramShopCountService.getTotalShopCount(startDate, endDate);
			diagramName = isEnglish ? "Most popular shops" : "Найпопулярніші магазини";
		}
		
		JSONObject responseObject = new JSONObject();
		JSONArray series = new JSONArray();
		JSONObject inSeries = new JSONObject();
		JSONObject xAxis = new JSONObject();
		xAxis.put("type", isEnglish ? "Shops" : "Магазини");
		responseObject.put("xAxis", xAxis);
		JSONObject yAxis = new JSONObject();
		JSONObject titleAxis = new JSONObject();
		titleAxis.put("text", isEnglish ? "Popularity" : "Популярність");
		yAxis.put("title", titleAxis);
		responseObject.put("yAxis", yAxis);
		JSONArray data = new JSONArray();
		inSeries.put("colorByPoint", true);
		JSONObject title = new JSONObject();
		title.put("text", diagramName);
		responseObject.put("title", title);
		inSeries.put("name", isEnglish ? "Shop" : "Магазин");
		for (int i=0; i<shopsCount.size(); i++){
			JSONObject shop = new JSONObject();
			String shopName = isEnglish ? shopsCount.get(i).getNameEnglish() : shopsCount.get(i).getNameUkrainian() ;
			shop.put("name", shopName);
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
