package com.epam.easyshopway.dao;

import static org.junit.Assert.*;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.Date;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import com.epam.easyshopway.connection.ConnectionManager;
import com.epam.easyshopway.dao.UserDAO;
import com.epam.easyshopway.model.User;

public class UserDAOTest {
	private UserDAO userDAO;
	private static Connection connection;
	private User firstUser;
	private User secondUser;

//	@BeforeClass
//	public static void setUpBeforeClass() throws Exception {
//		connection = ConnectionManager.getInstance().getConnectionPool().getConnection();
//	}
//
//	@AfterClass
//	public static void tearDownAfterClass() throws Exception {
//		ConnectionManager.getInstance().getConnectionPool().putConnection(connection);
//	}
//
//	@Before
//	public void setUp() throws Exception {
//		firstUser = new User(12, "Unit", "Tester", "unittester@gmail.com", "111111", new Date(111111111), true, "user",
//				"ua", "\\test\\1.img");
//		secondUser = new User(12, "Mock", "Tester", "mocktester@gmail.com", "222222", new Date(111111111), true, "user",
//				"ua", "\\test\\2.img");
//	}
	
	@Test
	public void testInsert() throws Exception {
		try {
			firstUser = new User(12, "Unit", "Tester", "unittester@gmail.com", "111111", new Date(111111111), true, "user",
					"ua", "\\test\\1.img");
			secondUser = new User(12, "Mock", "Tester", "mocktester@gmail.com", "222222", new Date(111111111), true, "user",
					"ua", "\\test\\2.img");
//			Class userDAOClass = UserDAO.class;
			//Field field = userDAOClass.getField("connection");
//			Field field = AbstractDAO.class.getField("connection");
//			field.setAccessible(true);
//			System.out.println(AbstractDAO.class.isAssignableFrom(userDAOClass));
//			System.out.println(userDAOClass.getClass().getSuperclass().getName());
//			Field[] fs = userDAOClass.getClass().getSuperclass().getDeclaredFields();
//			connection = (Connection) field.get(userDAO);
//	        fs[0].setAccessible(true);
//	        for (Field fields : fs) {
//	        	System.out.println(fields.getName());
//	        }
//	        System.out.println(fs[0].getName());
			//Connection connection = (Connection) field.get(userDAO);
			//connection.setAutoCommit(false);
			userDAO.insertUser(firstUser);
			userDAO.insertUser(secondUser);
			User insertedFirstUser = userDAO.getByEmail(firstUser.getEmail());
			User insertedSecondUser = userDAO.getByEmail(secondUser.getEmail());
			assertEquals(firstUser.getFirstName(), insertedFirstUser.getFirstName());
			assertEquals(firstUser.getImage(), insertedFirstUser.getImage());
			assertEquals(firstUser.getLanguage(), insertedFirstUser.getLanguage());
			assertEquals(secondUser.getLastName(), insertedSecondUser.getLastName());
			assertEquals(secondUser.getPassword(), insertedSecondUser.getPassword());
			assertEquals(secondUser.getEmail(), insertedSecondUser.getEmail());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
