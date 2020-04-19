
package com.caremarque.hospital.model;

public class Hospital {

	private String hospitalId;
	private String hospitalName;
	private String phone;
	private String regNo;
	private String address;
	private String Open_Hours;
	private String Close_Hours;
	private String email;
	private String channelingFee;
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getChannelingFee() {
		return channelingFee;
	}

	public void setChannelingFee(String channelingFee) {
		this.channelingFee = channelingFee;
	}

	

	public Hospital() {
		super();
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getRegNo() {
		return regNo;
	}

	public void setRegNo(String regNo) {
		this.regNo = regNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpen_Hours() {
		return Open_Hours;
	}

	public void setOpen_Hours(String Open_Hours) {
		this.Open_Hours = Open_Hours;
	}

	public String getClose_Hours() {
		return Close_Hours;
	}

	public void setClose_Hours(String Close_Hours) {
		this.Close_Hours = Close_Hours;
	}

	public Hospital(String hospitalId, String hospitalName, String phone, String regNo, String address,
			String Open_Hours, String Close_Hours,String email,String channelingFee) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.regNo = regNo;
		this.address = address;
		this.Open_Hours = Open_Hours;
		this.Close_Hours = Close_Hours;
		this.email = email;
		this.channelingFee = channelingFee;
	}
}