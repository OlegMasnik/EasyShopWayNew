package com.epam.easyshopway.model;

import java.sql.Date;

import com.epam.easyshopway.dao.transformer.annotation.Column;

public class User {
	@Column("id")
	private Integer id;

	@Column("first_name")
	private String firstName;

	@Column("last_name")
	private String lastName;

	@Column("email")
	private String email;

	@Column("password")
	private String password;

	@Column("date_of_birth")
	private Date dateOfBirth;

	@Column("active")
	private Boolean active;

	@Column("role")
	private String role;
	
	@Column("language")
	private String language;

	public User() {
		super();
	}

	public User(String firstName, String lastName, String email, String password,
			Boolean active, String role, String language) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.active = active;
		this.role = role;
		this.language = language;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		if ("null".equalsIgnoreCase(dateOfBirth)) {
			this.dateOfBirth = new Date(new java.util.Date().getTime());
		} else {
			this.dateOfBirth = Date.valueOf(dateOfBirth);
		}
	}

	public Boolean isActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}
}
