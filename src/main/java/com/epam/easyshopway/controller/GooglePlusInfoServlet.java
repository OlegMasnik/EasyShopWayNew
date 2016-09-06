package com.epam.easyshopway.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.GooglePlusUserJSON;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * Servlet implementation class GooglePlusInfoServlet
 */
public class GooglePlusInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public GooglePlusInfoServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// Check if the user have rejected
		String error = req.getParameter("error");
		HttpSession sess = req.getSession();
		if ((null != error) && ("access_denied".equals(error.trim()))) {
			sess.invalidate();
			resp.sendRedirect(req.getContextPath());
			return;
		}
		OAuth20Service serivce = (OAuth20Service) sess.getAttribute("oauth2Service");
		String code = req.getParameter("code");
		OAuth2AccessToken token = serivce.getAccessToken(code);
		sess.setAttribute("token", token);
		OAuthRequest oReq = new OAuthRequest(Verb.GET, "https://www.googleapis.com/oauth2/v2/userinfo", serivce);
		serivce.signRequest(token, oReq);
		Response oResp = oReq.send();
		String json = oResp.getBody();
		GooglePlusUserJSON googlePlusUser = JSON.parseObject(json, GooglePlusUserJSON.class);
		System.out.println(oResp.getBody());
		User user = new User(googlePlusUser.getGiven_name(), googlePlusUser.getFamily_name(), googlePlusUser.getEmail(),
				null, // password
				true, // active user
				"user", "en", googlePlusUser.getPicture()); // native language
		User invokedUser = UserService.getByEmail(user.getEmail());
		if (invokedUser == null) {
			UserService.insert(user);
			sess.setAttribute("user", user);
		} else {
			sess.setAttribute("user", invokedUser);
		}
		resp.sendRedirect(req.getContextPath() + "/cabinet");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
