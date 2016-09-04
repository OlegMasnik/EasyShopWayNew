package com.epam.easyshopway.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.github.scribejava.apis.GoogleApi20;
import com.github.scribejava.core.builder.ServiceBuilder;
import com.github.scribejava.core.oauth.OAuth20Service;

/**
 * Servlet implementation class GooglePlusLoginServlet
 */
public class GooglePlusLoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private static String CLIENT_ID = "15529086780-fipdmpnmipg3j8f9u85ne5ac4hqtnt31.apps.googleusercontent.com";
	private static String CLIENT_SECRET = "MbOfz2ZmSU80_dPS34jSV0AB";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public GooglePlusLoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
    		throws ServletException, IOException { 
    		OAuth20Service service = new ServiceBuilder() 
    		.apiKey(CLIENT_ID) 
    		.apiSecret(CLIENT_SECRET) 
    		.callback("http://localhost:8080/EasyShopWayNew/home/google-plus-user-info")
    		.scope("openid profile email "
    				+ "https://www.googleapis.com/auth/plus.login "
    				+ "https://www.googleapis.com/auth/plus.me")
    		.build(GoogleApi20.instance()); 
    		HttpSession sess = request.getSession(); 
    		sess.setAttribute("oauth2Service", service); 
    		// String authUrl = service.getAuthorizationUrl(); 
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
