package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONArray;
import com.epam.easyshopway.service.MapService;

public class AdminMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private JSONArray mapNameArray;
       
    public AdminMapServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		type = request.getParameter("type");
		
		switch (type) {
		case "mapsName":
			mapNameArray = new JSONArray();
//			mapNameArray.
			break;

		case "map":
			
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}