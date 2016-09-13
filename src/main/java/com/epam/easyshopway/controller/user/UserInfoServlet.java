package com.epam.easyshopway.controller.user;

import java.io.IOException;

import javax.mail.Session;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;

public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UserInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		User user = (User) request.getSession().getAttribute("user");

		JSONObject object = new JSONObject();
		object.put("id", user.getId());
		object.put("firstName", user.getFirstName());
		object.put("lastName", user.getLastName());
		object.put("language", user.getLanguage());
		object.put("email", user.getEmail());
		object.put("img", user.getImage());

		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(object.toString());

	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		
		String firstName = request.getParameter("firstName");
		String lastName = request.getParameter("lastName");
		String birthday = request.getParameter("birthday");
//		String email = request.getParameter("email");
		String language = request.getParameter("language");
		HttpSession session = request.getSession(false);
		

//		System.out.println(firstName + " " + lastName + " " + birthday + " " + email + "");

		user.setLanguage(language);
		session.setAttribute("lang", language);
//		user.setEmail(email);
		user.setFirstName(firstName);
		user.setLastName(lastName);

		UserService.update(user.getId(), user);
		
		request.getSession().setAttribute("user", user);
	}

}
