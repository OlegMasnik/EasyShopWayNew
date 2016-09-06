package com.epam.easyshopway.controller.admin;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.easyshopway.utils.MailUtil;

public class SendMailControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String emails;
	private String header;
	private String body;

	public SendMailControlServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		emails = request.getParameter("emails");
		header = request.getParameter("header");
		body = request.getParameter("body");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					MailUtil.sendEmail(emails, header, body);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();
		response.getWriter().write("ok");

	}
}
