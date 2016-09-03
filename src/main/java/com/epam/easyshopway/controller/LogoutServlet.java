package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.epam.easyshopway.model.User;

public class LogoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User user;

	public LogoutServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			HttpSession s = request.getSession(false);
			s.invalidate();
			response.sendRedirect("/EasyShopWayNew/home");
		} else {
			request.getRequestDispatcher("/WEB-INF/error.jsp").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
