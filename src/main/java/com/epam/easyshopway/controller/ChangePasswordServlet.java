package com.epam.easyshopway.controller;

import java.io.IOException;
import java.util.Locale;
import java.util.ResourceBundle;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		String oldPass = null;
		System.out.println(request.getParameter("oldPass"));
		if (request.getParameter("oldPass") != null) {
			oldPass = MD5Util.md5Custom(request.getParameter("oldPass"));
		}
		System.out.println(oldPass);
		String newPass = MD5Util.md5Custom(request.getParameter("newPass"));
		String curPass = user.getPassword();
		
		Locale locale = new Locale("en".equals(user.getLanguage()) ? "en" : "ua");
		ResourceBundle bundle = ResourceBundle.getBundle("/diagram_i18n/diagram", locale);
		if (oldPass == null || curPass.equals(oldPass)){
			user.setPassword(newPass);
			int r = UserService.update(user.getId(), user);
			System.out.println(r);
			HttpSession session = request.getSession(false);
			session.setAttribute("user", user);
			object.put("msg", bundle.getString("successPass"));
		}else {
			object.put("msg", bundle.getString("incorrectPass"));
		}
		response.getWriter().write(object.toString());
	}
}
