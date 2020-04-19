package com.caremarque.patient.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class loginAuthentication {
	
	private int loginId;
	private String userName;
	private String password;
	private String type;
	
	public loginAuthentication() {
		super();
	}

	public loginAuthentication(int loginId, String userName, String password, String type) {
		super();
		this.loginId = loginId;
		this.userName = userName;
		this.password = password;
		this.type = type;
	}

	public int getLoginId() {
		return loginId;
	}

	public void setLoginId(int loginId) {
		this.loginId = loginId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	

}
