package com.caremarque.hospital.model;

public class Hospital {

	private Integer hospitalId;
	private String hospitalName;
	private String phone;
	private String regNo;
	private String address;
	
	
	
	public Hospital() {
		// TODO Auto-generated constructor stub
	}
	public Integer getHospitalId() {
		return hospitalId;
	}
	public void setHospitalId(Integer hospitalId) {
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
	
	public Hospital(Integer hospitalId, String hospitalName, String phone, String regNo, String address) {
		super();
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.phone = phone;
		this.regNo = regNo;
		this.address = address;
	}

}