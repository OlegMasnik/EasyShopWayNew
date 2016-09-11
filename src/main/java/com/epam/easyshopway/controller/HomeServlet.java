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

public class HomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HomeServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession(false);
		if(session == null)
			session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		String prevLang = (String) session.getAttribute("lang");
		System.out.println(user + " " + prevLang);
		if (user == null) {
			session.setAttribute("lang", prevLang);
		} else {
			System.out.println("Get user lang" + user.getLanguage());
			session.setAttribute("lang", user.getLanguage());
		}
		request.getRequestDispatcher("/WEB-INF/index.jsp").forward(request, response);
	}

	@Override
	protected void doPut(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String lang = request.getParameter("lang");
		HttpSession session = request.getSession(false);
		User user = (User) session.getAttribute("user");
		String prevLang = (String) session.getAttribute("lang");
		if (user != null && !(user.getLanguage().equals(lang))) {
			user.setLanguage(lang);
			UserService.update(user.getId(), user);
			session.setAttribute("user", user);
		}
		session.setAttribute("lang", lang);
	}

}
