package com.epam.easyshopway.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.epam.easyshopway.astar.NearestNeighborhood;
import com.epam.easyshopway.model.CupboardInformation;
import com.epam.easyshopway.model.Map;
import com.epam.easyshopway.model.Placement;
import com.epam.easyshopway.model.ProductInformation;
import com.epam.easyshopway.model.ProductList;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.CupboardInformationService;
import com.epam.easyshopway.service.MapService;
import com.epam.easyshopway.service.PlacementService;
import com.epam.easyshopway.service.ProductInformationService;
import com.epam.easyshopway.service.ProductListService;

public class SearchServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SearchServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String uri = request.getRequestURI();

		if (uri.endsWith("search")) {

			request.getRequestDispatcher("/WEB-INF/temp.jsp").forward(request, response);
		} else if (uri.endsWith("searchProducts")) {

			Integer mapId = Integer.valueOf(request.getParameter("mapId"));

			System.out.println(mapId);

			List<ProductInformation> products = ProductInformationService.getAllProductByMapId(mapId);
			for (int i = 0; i < products.size(); i++) {
				products.get(i).setCoordinates();
			}
			JSONObject object = new JSONObject();
			object.put("products", setJsonArrayType(products));

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		} else if (uri.endsWith("searchMaps")) {

			List<Map> maps = MapService.getAll();

			JSONObject object = new JSONObject();
			JSONArray arr = new JSONArray();

			for (Map map : maps) {
				JSONObject obj = new JSONObject();

				obj.put("id", map.getId());
				obj.put("name_uk", map.getNameUk());
				obj.put("name_en", map.getNameEn());

				arr.add(obj);
			}

			object.put("maps", arr);

			response.setCharacterEncoding("UTF-8");
			response.getWriter().write(object.toString());
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		User user = (User) request.getSession().getAttribute("user");

//		new Thread(new Runnable() {
//
//			@Override
//			public void run() {

				JSONParser jsonParser = new JSONParser();
				JSONObject jsonObject;
				List<Long> productIds = new ArrayList<Long>();

				Integer mapId = null;
				try {

					jsonObject = (JSONObject) jsonParser.parse(request.getParameter("data"));
					// System.out.println(jsonObject);
					productIds = (List<Long>) jsonObject.get("productIds");
					mapId = ((Long) jsonObject.get("mapId")).intValue();
					Integer width = ((Long) jsonObject.get("width")).intValue();
					Integer height = ((Long) jsonObject.get("height")).intValue();
					Long enter = ((Long) jsonObject.get("enter"));

					List<Long> walls = (List<Long>) jsonObject.get("walls");
					List<Long> paydesks = (List<Long>) jsonObject.get("paydesks");
					List<Long> cupboards = (List<Long>) jsonObject.get("cupboards");
					List<List<Long>> products = (List<List<Long>>) jsonObject.get("products");
					
					JSONArray path = new JSONArray();
					JSONArray visited = new JSONArray();
					JSONObject obj = new JSONObject();
					
					if(enter != null && paydesks != null && paydesks.size() > 0){
						NearestNeighborhood n = new NearestNeighborhood(width, height, walls, paydesks, products,
								cupboards);
						n.start(enter.intValue());
						path.addAll(n.path);
						visited.addAll(n.visited);
					}
					obj.put("path", path);
					obj.put("visited", visited);
					try {
						response.getWriter().write(obj.toJSONString());
					} catch (IOException e) {
						e.printStackTrace();
					}

				} catch (ParseException e) {
					e.printStackTrace();
				}

				if (user != null) {
					ProductList productList = new ProductList(user.getId(), null, null, mapId);

					System.out.println(
							productList.getDate() + " " + productList.getTime() + " " + productList.getUserId());
					ProductListService.insertListAndProduct(productList, productIds);
				} else {
					System.out.println("No user");
				}
//			}
//		}).start();
	}

	private JSONArray setJsonArrayType(Collection<ProductInformation> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (ProductInformation p : list) {
			if (p.getCoordinates().size() > 0) {
				Set<Integer> setCoard = new HashSet<>(p.getCoordinates());
				object = new JSONObject();
				object.put("img", p.getImage());
				object.put("name_uk", p.getProductNameUk());
				object.put("name_en", p.getProductNameEn());
				object.put("id", p.getId());

				JSONArray cordArr = new JSONArray();

				for (Integer cord : setCoard) {
					cordArr.add(cord);
				}

				object.put("coordinates", cordArr);
				jsonArray.add(object);
			}
		}
		return jsonArray;
	}
}
