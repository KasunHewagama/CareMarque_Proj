package com.caremarque.userAuth.model;

public class userAuth {

	private int userAuthId;
	private String email;
	private String password;
	
	public userAuth() {}

	public int getUserAuthId() {
		return userAuthId;
	}

	public void setUserAuthId(int userAuthId) {
		this.userAuthId = userAuthId;
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
