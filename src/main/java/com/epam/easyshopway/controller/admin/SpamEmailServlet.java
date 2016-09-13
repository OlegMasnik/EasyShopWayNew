package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.mail.MessagingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.alibaba.fastjson.JSON;
import com.epam.easyshopway.utils.EmailReceiveJSON;
import com.epam.easyshopway.utils.MailUtil;

public class SpamEmailServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String header;
	private String body;

	public SpamEmailServlet() {
		super();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		JSONParser parser = new JSONParser();
		JSONArray obj = null;
		try {
			obj = (JSONArray) parser.parse(request.getParameter("emails"));
		} catch (ParseException e1) {
			e1.printStackTrace();
		}
		List<EmailReceiveJSON> userList = new ArrayList<>();
		for (Object ob : obj.toArray()) {
			System.out.println(ob.toString());
			userList.add(JSON.parseObject(ob.toString(), EmailReceiveJSON.class));
		}
		header = request.getParameter("header");
		body = request.getParameter("body");
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try {
					for (EmailReceiveJSON email : userList) {
						MailUtil.sendEmail(email.getEmail(), header, body);
					}
				} catch (MessagingException e) {
					e.printStackTrace();
				}
			}
		}).start();
		response.getWriter().write("ok");

	}

}
