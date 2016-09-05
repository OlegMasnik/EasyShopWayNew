package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;

public class UsersControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONObject o;

	public UsersControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("get all users");
		o = new JSONObject();
		System.out.println(UserService.getAll());
		o.put("users", setJsonArray(UserService.getAll()));
		System.out.println(o.toJSONString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(o.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

	private JSONArray setJsonArray(Collection<User> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (User u : list) {
			object = new JSONObject();
			object.put("fn", u.getFirstName());
			object.put("ln", u.getLastName());
			object.put("e", u.getEmail());
			object.put("active", u.isActive());
			jsonArray.add(object);
		}
		return jsonArray;
	}

}

