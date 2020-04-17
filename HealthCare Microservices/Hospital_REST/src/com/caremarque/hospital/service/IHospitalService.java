package com.caremarque.hospital.service;

import java.util.ArrayList;

import com.caremarque.hospital.model.Hospital;

public interface IHospitalService {
	
	public String createHospital(Hospital hospital);
	
	public String getHospital(String hospitalId);
	
	public String getHospitals();
	
	public String updateHospital(String hospitalId,Hospital hospital);
	
	public String DeleteHospital(String hospitalId);
	
	public ArrayList<String> getHospitalIDs();

}
