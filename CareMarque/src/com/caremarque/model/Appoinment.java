package com.caremarque.model;

import java.sql.Time;
import java.util.Date;

public class Appoinment {

	private String appointmentId;
	private String patientId;
	private String patientName;
	private String phone;
	private String doctorName;
	private String hospitalName;
	private String hospitalId;
	private Date appointmentDate;
	private Date lastUpdateDate;
	private Time appointmentTime;
	private Time lastUpdateTime;
	private String specialization;

	public Appoinment() {
		// TODO Auto-generated constructor stub
	}

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getDoctorName() {
		return doctorName;
	}

	public void setDoctorName(String doctorName) {
		this.doctorName = doctorName;
	}

	public String getHospitalName() {
		return hospitalName;
	}

	public void setHospitalName(String hospitalName) {
		this.hospitalName = hospitalName;
	}

	public String getHospitalId() {
		return hospitalId;
	}

	public void setHospitalId(String hospitalId) {
		this.hospitalId = hospitalId;
	}

	public Date getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(Date appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public Date getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(Date lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public Time getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Time appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public Time getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(Time lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public Appoinment(String appointmentId, String patientId, String patientName, String phone, String doctorName,
			String hospitalName, String hospitalId, Date appointmentDate, Date lastUpdateDate, Time appointmentTime,
			Time lastUpdateTime, String specialization) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.patientName = patientName;
		this.phone = phone;
		this.doctorName = doctorName;
		this.hospitalName = hospitalName;
		this.hospitalId = hospitalId;
		this.appointmentDate = appointmentDate;
		this.lastUpdateDate = lastUpdateDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdateTime = lastUpdateTime;
		this.specialization = specialization;
	}
	
	

}
