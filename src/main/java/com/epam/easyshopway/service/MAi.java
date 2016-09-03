package com.epam.easyshopway.service;

import com.epam.easyshopway.model.User;

public class MAi {

	public static void main(String[] args) {
		User u = UserService.getByEmail("olegmasnik@gmail.com");
		System.out.println(u);
	}
}
