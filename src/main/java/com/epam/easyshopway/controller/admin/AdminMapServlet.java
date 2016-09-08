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
import com.epam.easyshopway.model.CupboardInformation;
import com.epam.easyshopway.model.Map;
import com.epam.easyshopway.model.Placement;
import com.epam.easyshopway.service.CupboardInformationService;
import com.epam.easyshopway.service.MapService;
import com.epam.easyshopway.service.PlacementService;

public class AdminMapServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String type;
       
    public AdminMapServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setCharacterEncoding("utf-8");
		type = request.getParameter("type");
		
		
		switch (type) {
		case "mapsName":
			JSONArray responseJSON = doForMapsName();
			response.getWriter().write(responseJSON.toString());
			
			break;

		case "map":
			Integer mapId = Integer.valueOf(request.getParameter("id"));
			JSONObject responseJson = doForMap(mapId);
			responseJson = doForMap(mapId);
			response.getWriter().write(responseJson.toString());
				
			break;
			
		case "open":
			
			
			break;
		}
			
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
	
	private JSONArray doForMapsName(){
		JSONArray mapNameArray = new JSONArray();
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
		return mapNameArray;
	}
	
	private JSONObject doForMap (Integer mapId){
		JSONObject response = new JSONObject();
	
		Map map = MapService.getById(mapId);
		if (map != null){
			JSONObject m = new JSONObject();
			m.put("id", map.getId());
			m.put("weight", map.getWeight());
			m.put("height", map.getHeight());
			m.put("nameEn", map.getNameEn());
			m.put("nameUk", map.getNameUk());
			response.put("map", m);
		
			
			JSONArray enters = getPlaces(PlacementService.getEntersByMapId(mapId));
			JSONArray paydesks = getPlaces(PlacementService.getPayDesksByMapId(mapId));
			JSONArray walls = getPlaces(PlacementService.getWallsByMapId(mapId));
			JSONArray cupboards = cupboardsToJSON(CupboardInformationService.getCupboardsByMapId(mapId));
			
			
			response.put("enters", enters);
			response.put("walls", walls);
			response.put("paydesks", paydesks);
			response.put("cupboards", cupboards);
		}
		return response;
	}
			
	private JSONArray getPlaces(List<Placement> placements){
		JSONArray places = new JSONArray();
		if (placements != null){
			for (Placement placement : placements)
				places.add(placement.getPlace());
		}
		return places;
	}
	
	private JSONArray cupboardsToJSON (List<CupboardInformation> cupboards){
		JSONArray result = new JSONArray();
		if (cupboards != null){
			int size = cupboards.size();
			for (int i=0; i<size; i++){
				JSONObject cupboard = new JSONObject();
				JSONArray values = new JSONArray();
				Integer id = cupboards.get(i).getCupboardId();
				cupboard.put("id", id);
				do{
					values.add(cupboards.get(i++).getPlace());
				}while (i<size && id.equals(cupboards.get(i).getCupboardId()));
				i--;
				cupboard.put("values", values);
				result.add(cupboard);
			}
		}
		return result;
	}		
}


