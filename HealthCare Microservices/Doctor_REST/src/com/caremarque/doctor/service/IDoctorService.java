package com.caremarque.doctor.service;

import java.util.ArrayList;

import com.caremarque.doctor.model.Doctor;

public interface IDoctorService {

	public String createDoctor(Doctor doctor);

	public ArrayList<String> getDoctorIDs();

	public String getDoctor(String doctorId);

	public String getDoctors();

	//public String updateDoctor(Doctor doctor);

	public String cancelDoctor(String doctorId);

	public String updateDoctor(String doctorId, Doctor doctor);

	
	//to get number of appointments
	public String getAllAppointments(String doctorId); 
	
	public String login(Doctor doctor);
}
