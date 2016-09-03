package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.MD5Util;

public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONObject object;
	private User user;
	private String email;
	private String password;

	public LoginServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		object = new JSONObject();
		
		
		
		if(request.getSession().getAttribute("pre_user") != null){
			object.put("emailErrMsg", "Please check your email.");
		}else{
			
			email = request.getParameter("email");
			password = request.getParameter("password");
			
			user = UserService.getByEmail(email);
			System.out.println(user);
			if (user == null) {
				object.put("emailErrMsg", "Uncorrect email.");
			} else if (user.getPassword().equals(MD5Util.md5Custom(password))) {
				HttpSession session = request.getSession(true);
				session.setAttribute("user", user);
				System.out.println(user.getFirstName());
			} else {
				object.put("passwordErrMsg", "Uncorrect password.");
			}
		}


		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(object.toString());
		System.out.println();
		System.out.println(object);
	}

}
