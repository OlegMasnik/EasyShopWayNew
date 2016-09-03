package com.epam.easyshopway.controller;

import java.io.IOException;
<<<<<<< HEAD
import java.util.Date;

=======

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
>>>>>>> master
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
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String email = request.getParameter("email");
		Date birthday = Date.request.getParameter("birthday");
		String password = request.getParameter("password");

		user = UserService.getByEmail(email);
		object = new JSONObject();

		if (user == null) {
			object.put("emailErrMsg", "Uncorrect email.");
		} else if (user.getPassword().equals(password)) {
			HttpSession session = request.getSession(true);
			session.setAttribute("user", user);

			System.out.println(user.getFirstName());

			response.sendRedirect("HomePage.do");
		} else {
			object.put("passwordErrMsg", "Uncorrect password.");
		}

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(object.toString());

		System.out.println(object);
	}
}
