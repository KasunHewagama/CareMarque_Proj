package com.caremarque.patient.service;

import java.util.List;

import com.caremarque.patient.model.Patient;

public interface IPatientService {

	public String registerPatient(Patient patient);
	
	public String getPatientDetailById(int patientId);
	
	public String getAllPatients();
	
	public String updatePatientDetails(Patient patient);

	public String deletePatient(String patientId);
	
	public List<String> getPatientIDs();
	
}
