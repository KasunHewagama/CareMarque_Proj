package com.caremarque.model;

import java.sql.Date;
import java.sql.Time;

public class Appointment {

	private int appointmentId;
	private String patientId;
	private String patientName;
	private String phone;
	private String doctorName;
	private String hospitalName;
	private String hospitalId;
	private String appointmentDate;
	private String lastUpdateDate;
	private String appointmentTime;
	private String lastUpdateTime;
	private String specialization;
	private String appointmentStatus;

	public Appointment() {
		// TODO Auto-generated constructor stub
	}

	public int getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(int appointmentId) {
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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}
	
	public Appointment(int appointmentId, String patientId, String patientName, String phone, String doctorName,
			String hospitalName, String hospitalId, String appointmentDate, String lastUpdateDate, String appointmentTime,
			String lastUpdateTime, String specialization, String appoinmentStatus) {
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
		this.appointmentStatus = appoinmentStatus;
	}
	
	

}
