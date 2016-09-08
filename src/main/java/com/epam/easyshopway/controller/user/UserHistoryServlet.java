	package com.epam.easyshopway.controller.user;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epam.easyshopway.model.FullProductList;
import com.epam.easyshopway.model.ProductType;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.FullProductListService;
import com.epam.easyshopway.service.ProductTypeService;
import com.sun.org.apache.bcel.internal.generic.RETURN;

/**
 * Servlet implementation class UserHistoryServlet
 */
public class UserHistoryServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public UserHistoryServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");

		if (user != null) {
			HashMap<Integer, List<FullProductList>> productLists = (HashMap<Integer, List<FullProductList>>) FullProductListService.getProductListByUserId(user.getId());
			
			JSONObject object = createJSON(productLists);
			
			System.out.println(object.toString());
			
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JSONArray setJsonArrayType(Collection<FullProductList> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (FullProductList p : list) {
			object = new JSONObject();
			object.put("img", p.getImage());
			object.put("name_uk", p.getProductNameUk());
			object.put("name_en", p.getProductNameEn());
			object.put("type_en", p.getTypeNameEn());
			object.put("type_uk", p.getTypeNameUk());
			object.put("date", p.getData().toString());
			object.put("time", p.getTime().toString());
			jsonArray.add(object);
		}
		return jsonArray;
	}
	
	private JSONObject createJSON (HashMap<Integer, List<FullProductList>> productLists) {
		
		JSONArray arr = new JSONArray();
		
		for(Entry<Integer, List<FullProductList>> entry : productLists.entrySet()) {
			JSONObject o = new JSONObject();
			o.put("products", setJsonArrayType(entry.getValue()));
			o.put("id", entry.getKey());
			arr.add(o);
		}
		
		JSONObject result = new JSONObject();
		result.put("lists", arr);
		
		return result;
	}

}
