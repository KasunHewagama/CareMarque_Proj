package com.caremarque.patient.service;

import java.util.ArrayList;

import com.caremarque.patient.model.Patient;

public interface IPatientService {

	public String registerPatient(Patient patient);
	
	//public Patient getPatient(String patientId);
	public String getPatientDetail(int patientId);
	
	public String getAllPatients();

	//public ArrayList<Patient> getPatients();
	
	//public Patient updatePatientDetails(String patientId, Patient patient);
	
	public String updatePatientDetails(Patient patient);

	public String deletePatient(String patientId);
	
}
