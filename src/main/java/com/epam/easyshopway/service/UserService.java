package com.epam.easyshopway.service;

import java.sql.SQLException;
import java.util.List;

import com.epam.easyshopway.dao.UserDAO;
import com.epam.easyshopway.model.User;

public class UserService {

	public static User insert(User user) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.insertUser(user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static List<User> getAll() {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.getAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User getById(Integer id) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.getById(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static User getByEmail(String email) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.getByEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException | InstantiationException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static int update(Integer userId, User user) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.update(userId, user);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int delete(Integer id) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.delete(id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int setActive(String email, boolean active) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.setActive(email, active);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static boolean validateUser(String email, String password) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.validateUser(email, password);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static boolean hasEmail(String email) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.hasEmail(email);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
	
	public static int updatePicture(Integer index, String imageAddress) {
		try (UserDAO userDAO = new UserDAO()) {
			return userDAO.updatePicture(index, imageAddress);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
}
