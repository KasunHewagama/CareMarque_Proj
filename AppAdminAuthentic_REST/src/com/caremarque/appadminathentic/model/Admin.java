package com.caremarque.appadminathentic.model;

public class Admin {
	private String adminId;
	private String username;
	private String email;
	private String password;
	public Admin() {
		super();
		
	}
	public Admin(String adminId,String username, String email, String password) {
		super();
		this.adminId=adminId;
		this.username = username;
		this.email = email;
		this.password = password;
	}
	
	public String getAdminId() {
		return adminId;
	}
	public void setAdminId(String adminId) {
		this.adminId = adminId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
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
	
	

}
