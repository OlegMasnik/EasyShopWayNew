package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.epam.easyshopway.utils.MailUtil;

public class SpamEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String header;
	private String body;

    public SpamEmailServlet() {
        super();
    }


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String[] emails = request.getParameterValues("emails");
		header = request.getParameter("header");
		body = request.getParameter("body");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				for (int i = 0; i < emails.length; i++)
				try {
					MailUtil.sendEmail(emails[i], header, body);
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}

}
