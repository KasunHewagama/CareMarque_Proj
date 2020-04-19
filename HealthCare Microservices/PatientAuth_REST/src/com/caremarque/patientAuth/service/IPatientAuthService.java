package com.caremarque.patientAuth.service;

import java.util.List;

import com.caremarque.patientAuth.model.PatientAuthentication;



public interface IPatientAuthService {

	public List<PatientAuthentication> checkPatientDetails();
}
