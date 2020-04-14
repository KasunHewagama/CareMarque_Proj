package com.caremarque.doctor.model;

public class Doctor
{

	private String doctorId;
	private String firstName;
	private String lastName;
	private String regNo;
	private String gender;
	private String specialization;
	private int phone;
	private String email;
	private String password;
	private String confirmPassword;
	
	
	
	public Doctor(String doctorId, String firstName, String lastName, String regNo, String gender,
			String specialization, int phone, String email, String password, String confirmPassword) {
		super();
		this.doctorId = doctorId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.regNo = regNo;
		this.gender = gender;
		this.specialization = specialization;
		this.phone = phone;
		this.email = email;
		this.password = password;
		this.confirmPassword = confirmPassword;
	}
	public String getDoctorId() {
		return doctorId;
	}
	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
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
	public String getRegNo() {
		return regNo;
	}
	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getSpecialization() {
		return specialization;
	}
	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}
	public int getPhone() {
		return phone;
	}
	public void setPhone(int phone) {
		this.phone = phone;
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
	public String getConfirmPassword() {
		return confirmPassword;
	}
	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}
	
	
	
}
