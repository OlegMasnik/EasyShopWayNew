package com.epam.easyshopway.filter;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

@WebFilter("/*")
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
		// Setting the character set for the request
		System.out.println("Inside doFilter");

		request.setCharacterEncoding("UTF-8");

		// Setting the character set for the response
		response.setContentType("text/html; charset=UTF-8");

		HttpServletRequest req = (HttpServletRequest) request;

		String uri = req.getRequestURI();
		StringBuffer url = req.getRequestURL();

		if ("/EasyShopWayNew/".equals(req.getRequestURI())) {
			req.getRequestDispatcher("/home").forward(request, response);
		}
		

		// pass the request on
		chain.doFilter(request, response);

	}

	public void destroy() {
		this.filterConfig = null;
	}

}
