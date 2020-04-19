package com.caremarque.patient.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientAuthentication {

	private int patientAuthId;
	private String userName;

	
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

	
	
}
