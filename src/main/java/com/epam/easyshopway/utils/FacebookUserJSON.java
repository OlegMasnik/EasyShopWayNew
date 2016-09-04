
package com.epam.easyshopway.utils;

public class FacebookUserJSON {
//	{
//		 "id":"4",
//		 "name":"Mark Zuckerberg",
//		 "first_name":"Mark",
//		 "last_name":"Zuckerberg",
//		 "link":"https:\/\/www.facebook.com\/zuck",
//		 "username":"zuck",
//		 "gender":"male",
//		 "locale":"en_US"
//		}
	private int id;
	private String name;
	private String first_name;
	private String last_name;
	private String link;
	private String username;
	private String gender;
	private String locale;
	public FacebookUserJSON(String id, String name, String first_name, String last_name, String link, String username,
			String gender, String locale) {
		super();
		this.id = Integer.parseInt(id);
		this.name = name;
		this.first_name = first_name;
		this.last_name = last_name;
		this.link = link;
		this.username = username;
		this.gender = gender;
		this.locale = locale;
	}
	public FacebookUserJSON() {
	}
	public int getId() {
		return id;
	}
	public void setId(String id) {
		this.id = Integer.parseInt(id);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getFirst_name() {
		return first_name;
	}
	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}
	public String getLast_name() {
		return last_name;
	}
	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getLocale() {
		return locale;
	}
	public void setLocale(String locale) {
		this.locale = locale;
	}
	

}
