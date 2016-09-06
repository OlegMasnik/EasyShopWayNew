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
import com.epam.easyshopway.model.ProductType;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.ProductService;
import com.epam.easyshopway.service.ProductTypeService;

public class TypeProductControlServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private JSONObject o;
	private User user;
	private ProductType productType;

	public TypeProductControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		o = new JSONObject();
		user = (User) request.getSession(false).getAttribute("user");
		o.put("types", setJsonArrayType(ProductTypeService.getAll()));
		System.out.println(o.toJSONString());
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(o.toJSONString());
	}

	@Override
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		System.out.println("Delete type " + id);
		ProductTypeService.delete(id);
	}

	private JSONArray setJsonArrayType(Collection<ProductType> list) {
		JSONArray jsonArray = new JSONArray();
		JSONObject object;
		for (ProductType u : list) {
			object = new JSONObject();
			object.put("id", u.getId());
			object.put("img", u.getImageUrl());
			object.put("nen", u.getNameEn());
			object.put("nuk", u.getNameUk());
			jsonArray.add(object);
		}
		return jsonArray;
	}

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nameEn = req.getParameter("nen");
		String nameUk = req.getParameter("nuk");
		String image = req.getParameter("img");
		System.out.println("Do Put " + id + " " + nameEn + " " + nameUk + " "
				+ image);
		if (id != 0) {
			productType = ProductTypeService.getById(id);
			productType.setNameEn(nameEn);
			productType.setNameUk(nameUk);
			productType.setImageUrl(image);
			if (ProductTypeService.update(id, productType) > 0) {
				System.out.println("OK put");
			} else {
				System.out.println("Bad put");
			}
		} else {
			productType = new ProductType();
			productType.setNameEn(nameEn);
			productType.setNameUk(nameUk);
			productType.setImageUrl(image);
			productType.setActive(true);
			if (ProductTypeService.insert(productType) > 0) {
				System.out.println("OK insert");
			} else {
				System.out.println("Bad insert");
			}
		}

	}

}
