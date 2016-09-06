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
import com.epam.easyshopway.model.UserProductType;
import com.epam.easyshopway.service.UserProductTypeService;
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
		List<UserProductType> userProducts = UserProductTypeService.getUserProductTypes(user.getId(), startDate, endDate);
		JSONObject responseObject = new JSONObject();
		JSONArray series = new JSONArray();
		JSONObject inSeries = new JSONObject();
		JSONArray data = new JSONArray();
		inSeries.put("colorByPoint", true);
		boolean isEnglish = "en".equals(user.getLanguage());
		responseObject.put("title", new JSONObject().put("text", isEnglish ? "Often searched groups of food:" : "����� �������, ��� �� ����� ������:"));
		inSeries.put("name", isEnglish ? "Persentage" : "� ���������:");
		for (int i=0; i<userProducts.size(); i++){
			JSONObject foodType = new JSONObject();
			String productTypeName = isEnglish ? userProducts.get(i).getNameEnglish() : userProducts.get(i).getNameEnglish() ;
			foodType.put("name", productTypeName);
			double percent = 1.0 / userProducts.get(i).getCount() * 100;
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
		response.getWriter().write(responseObject.toString());
	}
	
}
