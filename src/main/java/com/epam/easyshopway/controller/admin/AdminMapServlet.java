package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.epam.easyshopway.model.Map;
import com.epam.easyshopway.service.MapService;

public class AdminMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private JSONArray mapNameArray;
       
    public AdminMapServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		
		type = request.getParameter("type");
		
		switch (type) {
		case "mapsName":
			mapNameArray = new JSONArray();
			List<Map> maps = MapService.getAll();
			if (maps != null){
				for (Map i : maps){
					JSONObject map = new JSONObject();
					map.put("id", i.getId());
					map.put("nameEn", i.getNameEn());
					map.put("nameUk", i.getNameUk());
					mapNameArray.add(map);
				}
			}
			response.getWriter().write(mapNameArray.toString());
			break;

		case "map":
			Integer mapId = Integer.valueOf(request.getParameter("id"));
			Map map = MapService.getById(mapId);
			if (map != null){
				JSONObject m = new JSONObject();
				m.put("map", map);
				response.getWriter().write(map.toString());
			}
			break;
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
