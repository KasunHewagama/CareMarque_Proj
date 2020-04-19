package com.caremarque.patientAuth.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PatientAuthentication {

	private int patientAuthId;
	private String patientId;
	private String firstName;
	private String phone;
	private String userName;

	public PatientAuthentication() {
		super();
	}

	public PatientAuthentication(int patientAuthId, String patientId, String firstName, String phone, String userName) {
		super();
		this.patientAuthId = patientAuthId;
		this.patientId = patientId;
		this.firstName = firstName;
		this.phone = phone;
		this.userName = userName;
	}

	public int getPatientAuthId() {
		return patientAuthId;
	}

	public void setPatientAuthId(int patientAuthId) {
		this.patientAuthId = patientAuthId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}


	
}
