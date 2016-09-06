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

/**
 * Servlet implementation class ChangePasswordServlet
 */
public class ChangePasswordServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePasswordServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		JSONObject object = new JSONObject();
		User user = (User) request.getSession().getAttribute("user");
		String oldPass = MD5Util.md5Custom(request.getParameter("oldPass"));
		String newPass = MD5Util.md5Custom(request.getParameter("newPass"));
		String oldPassInDB = user.getPassword();
		
		if (oldPassInDB.equals(oldPass)){
			user.setPassword(MD5Util.md5Custom(newPass));
			UserService.update(user.getId(), user);
		}else {
			object.put("errMsg", "Incorrect old password");
		}
		response.getWriter().write(object.toString());
	}

}
