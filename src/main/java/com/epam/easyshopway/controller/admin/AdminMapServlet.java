package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

import com.alibaba.fastjson.JSONArray;
import com.epam.easyshopway.model.Map;
import com.epam.easyshopway.model.Placement;
import com.epam.easyshopway.service.MapService;
import com.epam.easyshopway.service.PlacementService;

public class AdminMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String type;
	private JSONArray mapNameArray;
       
    public AdminMapServlet() {
        super();
    }

	@SuppressWarnings("unchecked")
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
			JSONObject responseObject = new JSONObject();
			Integer mapId = Integer.valueOf(request.getParameter("id"));
			Map map = MapService.getById(mapId);
			JSONObject m = new JSONObject();
			if (map != null){
				m.put("id", map.getId());
				m.put("weight", map.getWeight());
				m.put("height", map.getHeight());
				m.put("nameEn", map.getNameEn());
				m.put("nameUk", map.getNameUk());
				responseObject.put("map", m);
				
				JSONArray cells = new JSONArray();
				JSONArray cupboards = new JSONArray();
				List<Placement> placements = PlacementService.getcPlacementByMapId(mapId);
				if (placements != null){
					for (Placement placement : placements){
						JSONObject cell = new JSONObject();
						String placementType = placement.getType();
						Integer placementId = placement.getId();
						Integer place = placement.getPlace();
						if (placementType.equals("cupboard")){
							cell.put("id", placementId);
							cell.put("values", place);
							cupboards.add(cell);
						}else{
							cell.put("id", placementId);
							cell.put("type", placementType);
							cell.put("place", place);
							cells.add(cell);
						}
					}
				}
				responseObject.put("cells", cells);
				responseObject.put("cupboards", cupboards);
				response.getWriter().write(responseObject.toString());
				
				
			}
			break;
			
		case "open":
			
			
			break;
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
