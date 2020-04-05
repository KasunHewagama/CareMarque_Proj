package com.caremarque.model;

import java.sql.Date;

public class Patient {
	
	private String patientId;
	private String firstName;
	private String lastName;
	private String NIC;
	private Date DOB;
	private String phone;
	private String email;
	private String gender;
	private String password;
	private String confirmPassword;
	private String bloodGroup;
	private String chronic;
	private String allergy;
	
	
	public Patient() {
		
	}
	
	public Patient(String patientId, String firstName, String lastName, String nIC, Date dOB, String phone, String email,
			String gender, String password, String confirmPassword, String bloodGroup, String chronic, String allergy) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		NIC = nIC;
		DOB = dOB;
		this.phone = phone;
		this.email = email;
		this.gender = gender;
		this.password = password;
		this.confirmPassword = confirmPassword;
		this.bloodGroup = bloodGroup;
		this.chronic = chronic;
		this.allergy = allergy;
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
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getNIC() {
		return NIC;
	}
	public void setNIC(String nIC) {
		NIC = nIC;
	}
	public Date getDOB() {
		return DOB;
	}
	public void setDOB(Date dOB) {
		DOB = dOB;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	public String getBloodGroup() {
		return bloodGroup;
	}
	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}
	public String getChronic() {
		return chronic;
	}
	public void setChronic(String chronic) {
		this.chronic = chronic;
	}
	public String getAllergy() {
		return allergy;
	}
	public void setAllergy(String allergy) {
		this.allergy = allergy;
	}
	
	

}
