package com.caremarque.appointment.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Appointment {

	private String appointmentId;
	private String patientId;
	private String patientName;
	private String phone;
	private String doctorName;
	private String specialization;
	private String hospitalId;
	private String hospitalName;
	private String appointmentDate;
	private String appointmentTime;
	private String lastUpdateDate;
	private String lastUpdateTime;
	private String appointmentStatus;

	public Appointment() {
		// TODO Auto-generated constructor stub
		super();
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

	public String getSpecialization() {
		return specialization;
	}

	public void setSpecialization(String specialization) {
		this.specialization = specialization;
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

	public String getAppointmentDate() {
		return appointmentDate;
	}

	public void setAppointmentDate(String appointmentDate) {
		this.appointmentDate = appointmentDate;
	}

	public String getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(String appointmentTime) {
		this.appointmentTime = appointmentTime;
	}

	public String getLastUpdateDate() {
		return lastUpdateDate;
	}

	public void setLastUpdateDate(String lastUpdateDate) {
		this.lastUpdateDate = lastUpdateDate;
	}

	public String getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(String lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}

	public String getAppointmentStatus() {
		return appointmentStatus;
	}

	public void setAppointmentStatus(String appointmentStatus) {
		this.appointmentStatus = appointmentStatus;
	}

	public Appointment(String appointmentId, String patientId, String patientName, String phone, String doctorName,
			String specialization, String hospitalId, String hospitalName, String appointmentDate,
			String appointmentTime, String lastUpdateDate, String lastUpdateTime, String appointmentStatus) {
		super();
		this.appointmentId = appointmentId;
		this.patientId = patientId;
		this.patientName = patientName;
		this.phone = phone;
		this.doctorName = doctorName;
		this.specialization = specialization;
		this.hospitalId = hospitalId;
		this.hospitalName = hospitalName;
		this.appointmentDate = appointmentDate;
		this.appointmentTime = appointmentTime;
		this.lastUpdateDate = lastUpdateDate;
		this.lastUpdateTime = lastUpdateTime;
		this.appointmentStatus = appointmentStatus;
	}

}
