package com.epam.easyshopway.dao;

import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.epam.easyshopway.dao.UserDAO;
import com.epam.easyshopway.model.User;

public class UserDAOTest {
	private static UserDAO userDAO;
	private User firstUser = new User(12, "Unit", "Tester", "unittester@gmail.com", "111111", new Date(111111111), true,
			"user", "ua", "\\test\\1.img", "default");
	private User secondUser = new User(12, "Mock", "Tester", "mocktester@gmail.com", "222222", new Date(111111111),
			true, "user", "ua", "\\test\\2.img", "default");

	@BeforeClass
	public static void runBeforeClass() {
		userDAO = new UserDAO();
	}

	@AfterClass
	public static void runAfterClass() {
		try {
			userDAO.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testCRUDOperations() throws Exception {
		// try (UserDAO userDAO = new UserDAO()) {
		userDAO.insertUser(firstUser);
		userDAO.insertUser(secondUser);
		User insertedFirstUser = userDAO.getByEmail(firstUser.getEmail());
		User insertedSecondUser = userDAO.getByEmail(secondUser.getEmail());
		assertEquals("Comparimg first name", firstUser.getFirstName(), insertedFirstUser.getFirstName());
		assertEquals("Comparimg image", firstUser.getImage(), insertedFirstUser.getImage());
		assertEquals("Comparimg language", firstUser.getLanguage(), insertedFirstUser.getLanguage());
		assertEquals("Comparimg last name", secondUser.getLastName(), insertedSecondUser.getLastName());
		assertEquals("Comparimg password", secondUser.getPassword(), insertedSecondUser.getPassword());
		assertEquals("Comparimg email", secondUser.getEmail(), insertedSecondUser.getEmail());
		assertNotEquals("Comparing email with wrong email", "wrongemail@gmail.com", firstUser.getEmail());
		assertNotEquals("Comparing first name with wrong first name", "John Paul", firstUser.getFirstName());
		userDAO.updatePassword(firstUser.getEmail(), "asdasdasd");
		insertedFirstUser.setPassword("asdasdasd");
		assertNotEquals("Comparing new pass wiith previous one", firstUser.getPassword(),
				userDAO.getByEmail(secondUser.getEmail()).getPassword());
		assertNotEquals("Comparing changed pass matches", insertedFirstUser.getPassword(),
				userDAO.getByEmail(secondUser.getEmail()).getPassword());
		userDAO.delete(insertedFirstUser.getId());
		userDAO.delete(insertedSecondUser.getId());
		assertNull("Checking if first user is succesfully deleted from database",
				userDAO.getByEmail(firstUser.getEmail()));
		assertNull("Checking if second user is succesfully deleted from database",
				userDAO.getByEmail(secondUser.getEmail()));
		// }
	}
	
	@Test
	public void testUserValidation() throws Exception {
		userDAO.insertUser(firstUser);
		assertTrue("Checking if pass matches", userDAO.validateUser(firstUser.getEmail(), firstUser.getPassword()));
		assertFalse("Checking if pass matches", userDAO.validateUser(firstUser.getEmail(), firstUser.getPassword() + "asd"));
		userDAO.delete(userDAO.getByEmail(firstUser.getEmail()).getId());
	}
	
	@Test
	public void testCheckMail() throws Exception {
		userDAO.insertUser(firstUser);
		assertTrue("Checking if user with such email exists", userDAO.hasEmail(firstUser.getEmail()));
		//deleting user
		userDAO.delete(userDAO.getByEmail(firstUser.getEmail()).getId());
		assertFalse("Checking if user exists after deleting", userDAO.hasEmail(firstUser.getEmail()));
	}

}
