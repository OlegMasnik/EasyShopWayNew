package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;


public class UserInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
    public UserInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");
		JSONObject object = new JSONObject();
		object.put("firstName", user.getFirstName());
		object.put("lastName", user.getLastName());
		object.put("language", user.getLanguage());
		object.put("email", user.getEmail());
		object.put("birthday", user.getDateOfBirth().toString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(object.toString());
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
				response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
