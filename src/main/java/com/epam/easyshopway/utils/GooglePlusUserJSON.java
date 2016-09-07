package com.epam.easyshopway.utils;

public class GooglePlusUserJSON {
	private String email;
	private String family_name;
	private String gende;
	private String given_name;
	private String id;
	private String link;
	private String name;
	private String verified_email;
	private String picture;

	public GooglePlusUserJSON(String email, String family_name, String gende, String given_name, String id, String link,
			String name, String verified_email, String picture) {
		super();
		this.email = email;
		this.family_name = family_name;
		this.gende = gende;
		this.given_name = given_name;
		this.id = id;
		this.link = link;
		this.name = name;
		this.verified_email = verified_email;
		this.picture = picture;
	}

	public GooglePlusUserJSON() {
		super();
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFamily_name() {
		return family_name;
	}

	public void setFamily_name(String family_name) {
		this.family_name = family_name;
	}

	public String getGende() {
		return gende;
	}

	public void setGende(String gende) {
		this.gende = gende;
	}

	public String getGiven_name() {
		return given_name;
	}

	public void setGiven_name(String given_name) {
		this.given_name = given_name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getVerified_email() {
		return verified_email;
	}

	public void setVerified_email(String verified_email) {
		this.verified_email = verified_email;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}
}
