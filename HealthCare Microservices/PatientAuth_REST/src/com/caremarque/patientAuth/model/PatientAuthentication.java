package com.caremarque.patientAuth.model;

public class PatientAuthentication {

	private int patientAuthId;
	private String userName;

	public PatientAuthentication() {
		super();
	}

	public PatientAuthentication(int patientAuthId, String userName) {
		super();
		this.patientAuthId = patientAuthId;
		this.userName = userName;
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

}
