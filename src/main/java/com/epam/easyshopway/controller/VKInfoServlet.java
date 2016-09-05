package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONReader;
import com.epam.easyshopway.model.User;
import com.epam.easyshopway.service.UserService;
import com.epam.easyshopway.utils.FacebookUserJSON;
import com.epam.easyshopway.utils.GooglePlusUserJSON;
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
		OAuthRequest oReq = new OAuthRequest(Verb.GET, "https://api.vk.com/method/users.get", serivce);
		serivce.signRequest(token, oReq);
		Response oResp = oReq.send();
		String json = oResp.getBody();
		System.out.println(json);
//		FacebookUserJSON facebookUser = JSON.parseObject(json, FacebookUserJSON.class);
//		User user = new User(googlePlusUser.getName(), googlePlusUser.getFamily_name(), googlePlusUser.getEmail(),
//				null, // password
//				true, // active user
//				"user", "en"); // native language
//		if (UserService.getByEmail(user.getEmail()) == null) {
//			UserService.insert(user);
//		} else {
//			sess.setAttribute("user", user);
//		}
//		req.getRequestDispatcher("/cabinet").forward(req, resp);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
