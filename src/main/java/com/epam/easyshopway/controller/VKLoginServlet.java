package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.scribejava.apis.FacebookApi;
import com.github.scribejava.apis.VkontakteApi;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * Servlet implementation class FacebookLogin
 */
public class VKLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CLIENT_ID = "5617106";
	private static String CLIENT_SECRET = "Q1K8UMssAmCQTyfzH21G";
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public VKLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		OAuth20Service service = new ServiceBuilder() 
	    		.apiKey(CLIENT_ID) 
	    		.apiSecret(CLIENT_SECRET)
	    		.scope("email")
	    		.callback("http://localhost:8080/EasyShopWayNew/home/vk-user-info")
	    		.build(VkontakteApi.instance());
	    		
	    		HttpSession sess = request.getSession(); 
	    		sess.setAttribute("vkOauth2Service", service); 
	    		response.sendRedirect(service.getAuthorizationUrl(null)); 		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
