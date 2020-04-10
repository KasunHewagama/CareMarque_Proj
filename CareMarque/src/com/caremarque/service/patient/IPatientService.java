package com.caremarque.service.patient;

import java.util.ArrayList;

import com.caremarque.model.Patient;

public interface IPatientService {

	public String registerPatient(Patient patient);
	
	public Patient getPatient(String patientId);
	
	public String getPatients();

	//public ArrayList<Patient> getPatients();
	
	public String updatePatientDetails(String patientId, Patient patient);
	
	public String deletePatient(String patientId);
	
}
