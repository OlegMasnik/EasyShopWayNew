package com.epam.easyshopway.controller.admin;

import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.epam.easyshopway.model.Product;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.ProductService;
import com.epam.easyshopway.service.ProductTypeService;

public class ProductsControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONObject o;
	private User user;

	public ProductsControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Products get");
		o = new JSONObject();
		user = (User) request.getSession(false).getAttribute("user");
		o.put("prods", setJsonArrayProducts(ProductService.getAll()));
		System.out.println(o.toJSONString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(o.toJSONString());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("Product delete id " + id);
		ProductService.delete(id);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Products post");
	}

	private JSONArray setJsonArrayProducts(Collection<Product> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (Product u : list) {
			object = new JSONObject();
			object.put("id", u.getId());
			object.put("ptid", u.getProductTypeId());
			object.put("nen", u.getNameEn());
			object.put("tnen", ProductTypeService.getById(u.getProductTypeId()).getNameEn());
			object.put("nuk", u.getNameUk());
			object.put("tnuk", ProductTypeService.getById(u.getProductTypeId()).getNameUk());
			jsonArray.add(object);
		}
		return jsonArray;
	}

}
