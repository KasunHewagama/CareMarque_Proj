package com.caremarque.patient.model;

public class PatientAuthentication {

	private int patientAuthId;
	private String userName;
	private String password;
	
	public PatientAuthentication() {
		super();
	}

	public int getPatientAuthId() {
		return patientAuthId;
	}

	public void setPatientAuthId(int patientAuthId) {
		this.patientAuthId = patientAuthId;
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
	
}
