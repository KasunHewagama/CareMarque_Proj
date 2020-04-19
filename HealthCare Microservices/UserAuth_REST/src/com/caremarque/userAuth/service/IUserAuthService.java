package com.caremarque.userAuth.service;

import java.util.List;

import com.caremarque.userAuth.model.PatientAuthentication;

public interface IUserAuthService {

	public List<PatientAuthentication> checkPatientDetails();
}
