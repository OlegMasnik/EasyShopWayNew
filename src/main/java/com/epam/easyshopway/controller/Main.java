package com.epam.easyshopway.controller;

import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) throws ParseException {
		
		DateFormat sourceFormat = new SimpleDateFormat("dd-MM-yyyy");
		Date dateOfBirth = Date.valueOf("10:10:2016");
		
		System.out.println(dateOfBirth);
	}

}
