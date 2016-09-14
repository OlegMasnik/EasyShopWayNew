package com.epam.easyshopway.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.MD5Util;
import com.epam.easyshopway.utils.MailUtil;

public class RegistrationServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONObject object;

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
			String password = request.getParameter("password");
			
			if (password == null) {
				System.out.println();
				System.out.println("Password null");
			}
			
			System.out.println("User " + firstName + " " 
					+ lastName + " " 
					+ email + " " 
					+ password);
			
			password = MD5Util.md5Custom(request.getParameter("password"));
			
			User user = new User(firstName, lastName, email, password, true, "user", "en", "");
			

			System.out.println(firstName.equals(""));
			object = new JSONObject();
			if (firstName == "" || lastName == "" || email == "" || password == "") {
				object.put("emailErrMsg", "Please, fill all fields");
				System.out.println("empty form");
			}
			
			if (UserService.hasEmail(email)) {
				object.put("emailErrMsg", "This email already exists");
				System.out.println("This email has already exists.");
			} else {
				// new Thread(new ThreadMail(request, user)).start();
				new Thread(new Runnable() {

					@Override
					public void run() {
						try {
							MailUtil.sendEmailRegistrationLink(user.getEmail(),
									user.getFirstName() + " " + user.getLastName(),
									MD5Util.md5Custom(request.getParameter("password")));
						} catch (MessagingException e) {
							e.printStackTrace();
						}

					}
				}).start();
				object.put("emailSuccMsg", "This email already exists");
				System.out.println("Chech your email.");
				request.getSession().setAttribute("pre_user", user);
			}
			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
