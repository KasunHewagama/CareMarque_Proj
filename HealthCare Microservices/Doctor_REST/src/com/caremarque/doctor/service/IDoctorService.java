package com.caremarque.doctor.service;

import java.util.ArrayList;

import com.caremarque.doctor.model.Doctor;

public interface IDoctorService {

	//this method is to create doctor
	public String createDoctor(Doctor doctor);

	//this method is to return doctor list
	public ArrayList<String> getDoctorIDs();

	//this method is to create doctor
	public String getDoctor(String doctorId);

	//this method is to create doctor
	public String getDoctors();

	//this method is to create doctor
	public String cancelDoctor(String doctorId);

	//this method is to create doctor
	public String updateDoctor(String doctorId, Doctor doctor);

	
	//to get number of appointments
	public String getAllAppointments(String doctorId); 
	
	public String login(Doctor doctor);
}
