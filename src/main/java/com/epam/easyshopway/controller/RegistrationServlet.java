package com.epam.easyshopway.controller;

import java.io.IOException;


import javax.servlet.ServletException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.MD5Util;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private JSONObject object;

	public RegistrationServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			String firstName = request.getParameter("firstName");
			String lastName = request.getParameter("lastName");
			String email = request.getParameter("email");
			String birthday = request.getParameter("birthday");
			String password = MD5Util.md5Custom(request.getParameter("password"));
			User user = new User(firstName, lastName, email, password, true, "user", "en");
			user.setDateOfBirth(birthday);
			object = new JSONObject();
			if (UserService.insert(user) == null){
				object.put("emailErrMsg", "This email has already exists.");
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		}catch (Exception e){
			e.printStackTrace();
		}
 
	}
}
