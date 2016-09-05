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
import com.epam.easyshopway.utils.VKTokenJSON;
import com.epam.easyshopway.utils.VKUserJSON;
import com.github.scribejava.core.model.OAuth2AccessToken;
import com.github.scribejava.core.model.OAuthRequest;
import com.github.scribejava.core.model.Response;
import com.github.scribejava.core.model.Verb;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * Servlet implementation class FacebookInfoServlet
 */
public class VKInfoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VKInfoServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String error = req.getParameter("error");
		HttpSession sess = req.getSession();
		if ((null != error) && ("access_denied".equals(error.trim()))) {
			sess.invalidate();
			resp.sendRedirect(req.getContextPath());
			return;
		}
		OAuth20Service serivce = (OAuth20Service) sess.getAttribute("vkOauth2Service");
		String code = req.getParameter("code");
		OAuth2AccessToken token = serivce.getAccessToken(code);
		sess.setAttribute("token", token);
		VKTokenJSON vkTokenJSON = JSON.parseObject(token.getRawResponse(), VKTokenJSON.class);
		OAuthRequest oReq = new OAuthRequest(Verb.GET, "https://api.vk.com/method/users.get", serivce);
		System.out.println(token.getRawResponse());
		serivce.signRequest(token, oReq);
		Response oResp = oReq.send();
		String json = oResp.getBody();
		System.out.println(json);
		VKUserJSON vkUser = JSON.parseObject(json, VKUserJSON.class);
		User user = new User(vkUser.getFirst_name(), vkUser.getLast_name(), vkTokenJSON.getEmail(),
				null, // password
				true, // active user
				"user", "en", ""); // native language
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
