package com.caremarque.patient.service;

import java.util.ArrayList;

import com.caremarque.patient.model.Patient;

public interface IPatientService {

	public String registerPatient(Patient patient);
	
	public String getPatientDetail(int patientId);
	
	public String getAllPatients();
	
	public String updatePatientDetails(Patient patient);

	public String deletePatient(String patientId);
	
	//public ArrayList<String> getPatientIDs();
	
}
