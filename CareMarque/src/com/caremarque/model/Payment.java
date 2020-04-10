package com.caremarque.model;

import java.sql.Date;

public class Payment {

	private String paymentId;
	private String patientId;
	private String patientName;
	private String appointmentId;
	private String doctorId;
	private String hospitalId;
	private Date paymentDate;
	private double doctorCharges;
	private double hospitalCharges;
	private double totalAmount;
	private String paymentStatus;

	public Payment() {

	}

	public String getPaymentId() {
		return paymentId;
	}

	public void setPaymentId(String paymentId) {
		this.paymentId = paymentId;
	}

	public String getPatientId() {
		return patientId;
	}

	public void setPatientId(String patientId) {
		this.patientId = patientId;
	}

	public String getPatientName() {
		return patientName;
	}

	public void setPatientName(String patientName) {
		this.patientName = patientName;
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getDoctorId() {
		return doctorId;
	}

	public void setDoctorId(String doctorId) {
		this.doctorId = doctorId;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}

	public double getDoctorCharges() {
		return doctorCharges;
	}

	public void setDoctorCharges(double doctorCharges) {
		this.doctorCharges = doctorCharges;
	}

	public double getHospitalCharges() {
		return hospitalCharges;
	}

	public void setHospitalCharges(double hospitalCharges) {
		this.hospitalCharges = hospitalCharges;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	
	

}
