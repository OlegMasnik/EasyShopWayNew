package com.epam.easyshopway.controller;

import java.io.IOException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;

@WebServlet("/login")
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

		email = request.getParameter("email");
		password = request.getParameter("password");

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
