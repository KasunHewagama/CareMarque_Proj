package com.caremarque.userAuth.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Patient {

	
	private String patientId;
	private String firstName;
	private String lastName;
	private String gender;
	private String nic;
	private String dob;
	private String email;
	private String phone;
	private String bloodGroup;
	private String allergy;
	private String password;
	private String confirmPassword;

	public Patient() {
		super();

	}

	public Patient(String patientId, String firstName, String lastName, String gender, String nIC, String dOB,
			String email, String phone, String bloodGroup, String allergy, String password, String confirmPassword) {
		super();
		this.patientId = patientId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.gender = gender;
		nic = nIC;
		dob = dOB;
		this.email = email;
		this.phone = phone;
		this.bloodGroup = bloodGroup;
		this.allergy = allergy;
		this.password = password;
		this.confirmPassword = confirmPassword;
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

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getNIC() {
		return nic;
	}

	public void setNIC(String nIC) {
		nic = nIC;
	}

	public String getDOB() {
		return dob;
	}

	public void setDOB(String dOB) {
		dob = dOB;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getBloodGroup() {
		return bloodGroup;
	}

	public void setBloodGroup(String bloodGroup) {
		this.bloodGroup = bloodGroup;
	}

	public String getAllergy() {
		return allergy;
	}

	public void setAllergy(String allergy) {
		this.allergy = allergy;
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

}
