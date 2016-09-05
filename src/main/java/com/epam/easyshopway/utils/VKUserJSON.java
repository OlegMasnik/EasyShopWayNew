
package com.epam.easyshopway.utils;

public class VKUserJSON {

	private int uid;
	private String first_name;
	private String last_name;
	
	public VKUserJSON() {
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

	public int getUid() {
		return uid;
	}
	public void setId(String uid) {
		this.uid = Integer.parseInt(uid);
	}
	
}
