package com.epam.easyshopway.controller.admin;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;

import com.epam.easyshopway.service.UserService;

public class UsersControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONArray jsonArray;

	public UsersControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		jsonArray.addAll(UserService.getAll());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(jsonArray.toJSONString());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

	}

}
