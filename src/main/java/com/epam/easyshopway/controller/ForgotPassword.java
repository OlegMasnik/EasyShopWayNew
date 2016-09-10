package com.epam.easyshopway.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.MD5Util;
import com.epam.easyshopway.utils.MailUtil;

public class ForgotPassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ForgotPassword() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("forgot passswoerd");
		request.getRequestDispatcher("/WEB-INF/email/forgotPass.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		String password = req.getParameter("password");

		User user = UserService.getByEmail(email);
		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					MailUtil.sendResetPasswordLink(user.getEmail(), MD5Util.md5Custom(password));
				} catch (MessagingException e) {
					e.printStackTrace();
				}

			}
		}).start();
		req.getSession().setAttribute("reset_email", email);
		req.getSession().setAttribute("reset_pass", MD5Util.md5Custom(password));
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String email = req.getParameter("email");
		boolean b = UserService.hasEmail(email);
		resp.getWriter().write("" + b);
	}

}
