package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;

public class VerifyRegisteredEmailHash extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private User preUser;
	private String newPassSession;
	private String email;

	public VerifyRegisteredEmailHash() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String hash = request.getParameter("hash");
		String scope = request.getParameter("scope");

		if (scope.equals("activation")) {
			preUser = (User) request.getSession().getAttribute("pre_user");
			System.out.println(preUser);
			if (preUser == null) {
				request.getRequestDispatcher("/WEB-INF/email/emailError.jsp").forward(request, response);
			} else if (preUser.getPassword().equals(hash)) {
				request.getSession().setAttribute("pre_user", null);
				UserService.insert(preUser);
				request.getRequestDispatcher("/WEB-INF/email/emailSuccess.jsp").forward(request, response);
			}
		}
		if (scope.equals("resetPassword")) {
			newPassSession = (String) request.getSession().getAttribute("reset_pass");
			email = (String) request.getSession().getAttribute("reset_email");	
			if (newPassSession.equals("")) {
				request.getRequestDispatcher("/WEB-INF/email/passError.jsp").forward(request, response);
			} else if (newPassSession.equals(hash)) {
				UserService.updatePasswordByEmail(email, newPassSession);
				request.getSession().setAttribute("email", null);
				request.getSession().setAttribute("newPass", null);
				request.getRequestDispatcher("/WEB-INF/email/passSuccess.jsp").forward(request, response);
			}
		}
	}

}
