package com.caremarque.hospital.model;

public class Hospital {

	private String hospitalId;
	private String hospitalName;
	private String phone;
	private String regNo;
	private String address;
	private String Open_Hours;
	private String Close_Hours;
	
	public Hospital() {
		// TODO Auto-generated constructor stub
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

	public void setOpen_Hours(String open_Hours) {
		Open_Hours = open_Hours;
	}

	public String getClose_Hours() {
		return Close_Hours;
	}

	public void setClose_Hours(String close_Hours) {
		Close_Hours = close_Hours;
	}

	public Hospital(String hospitalId, String hospitalName, String phone, String regNo, String address,
			String open_Hours, String close_Hours) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.regNo = regNo;
		this.address = address;
		this.Open_Hours = open_Hours;
		this.Close_Hours = close_Hours;
	}

	

}