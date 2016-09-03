package com.epam.easyshopway.service;

import com.epam.easyshopway.model.User;

public class MAi {

	public static void main(String[] args) {
		boolean u = UserService.hasEmail("olegmasnik@gmail.com");
		System.out.println(u);
	}
}
