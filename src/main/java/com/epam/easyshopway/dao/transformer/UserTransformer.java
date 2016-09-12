package com.epam.easyshopway.dao.transformer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epam.easyshopway.model.User;

public class UserTransformer {
	public User getUser(ResultSet rs) throws SQLException {
		User user = null;
		while (rs.next()) {
			user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			if (rs.getString("password") != null) {
				user.setPassword(rs.getString("password"));//null
			}
			Boolean active;
			if (rs.getInt("active") == 1) {
				active = true;
			} else {
				active = false;
			}
			user.setRole(rs.getString("role"));
			user.setLanguage(rs.getString("language"));
			user.setActive(active);
			if (rs.getString("image") != null) {
				user.setImage(rs.getString("image"));
			}
			user.setEmail(rs.getString("email"));
		}
		return user;
	}
	
	public List<User> getAllUsers(ResultSet rs) throws SQLException {
		List<User> userList = new ArrayList<>();
		while (rs.next()) {
			User user = new User();
			user.setId(rs.getInt("id"));
			user.setFirstName(rs.getString("first_name"));
			user.setLastName(rs.getString("last_name"));
			if (rs.getString("password") != null) {
				user.setPassword(rs.getString("password"));//null
			}
			Boolean active;
			if (rs.getInt("active") == 1) {
				active = true;
			} else {
				active = false;
			}
			user.setRole(rs.getString("role"));
			user.setLanguage(rs.getString("language"));
			user.setActive(active);
			if (rs.getString("image") != null) {
				user.setImage(rs.getString("image"));
			}
			user.setEmail(rs.getString("email"));
			userList.add(user);
		}
		return userList;
	}


}
