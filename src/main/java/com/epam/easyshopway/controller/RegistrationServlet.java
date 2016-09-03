package com.epam.easyshopway.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
	private User user;

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
			DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
			Date dateOfBirth = (Date) sourceFormat.parse(birthday);
			String password = MD5Util.md5Custom(request.getParameter("password"));
			User user = new User(firstName, lastName, email, password, dateOfBirth, true, "user", "en");
			if (UserService.insert(user) == null){
				object.put("emailErrMsg", "This email has already exists.");
				response.setCharacterEncoding("UTF-8");
				response.getWriter().write(object.toString());
			}else 
				request.getRequestDispatcher("/SuccessRegisteration").forward(request, response);
		}catch (ParseException e){
			e.printStackTrace();
		}
 
	}
}
