package com.epam.easyshopway.controller;

import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.alibaba.fastjson.JSON;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;

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
			DateFormat sourceFormat = new SimpleDateFormat("dd/MM/yyyy");
			Date date = (Date) sourceFormat.parse(birthday);
			String password = request.getParameter("password");
			
		}catch (ParseException e){
			e.printStackTrace();
		}
 
//		user = UserService.getByEmail(email);
//		object = new JSONObject();
//
//		if (user == null) {
//			object.put("emailErrMsg", "Uncorrect email.");
//		} else if (user.getPassword().equals(password)) {
//			HttpSession session = request.getSession(true);
//			session.setAttribute("user", user);
//
//			System.out.println(user.getFirstName());
//
//			response.sendRedirect("HomePage.do");
//		} else {
//			object.put("passwordErrMsg", "Uncorrect password.");
//		}
//
//		response.setCharacterEncoding("UTF-8");
//		response.getWriter().write(object.toString());
//
//		System.out.println(object);
	}
}
