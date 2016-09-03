package com.epam.easyshopway.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class EncodingFilter implements Filter {
	// FilterConfig object
	private FilterConfig filterConfig = null;

	// Default constructor
	public EncodingFilter() {
		System.out.println("Request response encoder Filter object has been created");
	}

	// Intitialization method
	public void init(FilterConfig filterConfig) {
		this.filterConfig = filterConfig;
	}

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletResponse res = (HttpServletResponse) response;
		HttpServletRequest req = (HttpServletRequest) request;

		request.setCharacterEncoding("UTF-8");

		response.setContentType("text/html; charset=UTF-8");
		
		if ("/EasyShopWayNew/".equals(req.getRequestURI())) {
			res.sendRedirect("/EasyShopWayNew/home");
		} else
			chain.doFilter(request, response);
		
		

	}

	public void destroy() {
		this.filterConfig = null;
	}

}