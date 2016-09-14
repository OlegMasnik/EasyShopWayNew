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
	private Product prod;

	public ProductsControlServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println("Products get");
		o = new JSONObject();
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

	@Override
	protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		int id = Integer.parseInt(req.getParameter("id"));
		String nameEn = req.getParameter("nameEn");
		String nameUk = req.getParameter("nameUk");
		int ptid = Integer.parseInt(req.getParameter("ptid"));
		System.out.println("Do Put " + id + " " + nameEn + " " + nameUk + " " + ptid);
		JSONObject message = new JSONObject();
		if (id != 0) {
			prod = ProductService.getById(id);
			prod.setNameEn(nameEn);
			prod.setNameUk(nameUk);
			prod.setProductTypeId(ptid);
			String mes;
			if (ProductService.update(id, prod) > 0) {
				System.out.println("OK put");
			} else {
				System.out.println("Bad put");
			}
		} else {
			prod = new Product();
			prod.setNameEn(nameEn);
			prod.setNameUk(nameUk);
			prod.setProductTypeId(ptid);
			prod.setActive(true);
			if (ProductService.hasNameEn(nameEn)){
				message.put("msg", "This english name already exists");
				System.out.println("This english name already exists");
			}else if(ProductService.hasNameUk(nameUk)) {
				System.out.println(nameUk);
				message.put("msg", "This ukrainian name already exists");
				System.out.println("This ukrainian name already exists");
			}else if(ProductService.insert(prod) > 0){
				message.put("msg", "Product type added successfully");
				System.out.println("Product type added successfully");
			}else{
				message.put("msg", "Product type adding failed");
			}
		}
		resp.getWriter().write(message.toString());
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
