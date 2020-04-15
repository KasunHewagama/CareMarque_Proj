package com.caremarque.hospital.service;


import com.caremarque.hospital.model.Hospital;

public interface IHospitalService {
	
	public String createHospital(Hospital h);
	
	public Hospital getHospital(String hospitalId);
	
	public String getHospitals();
	
	
	
	public String updateHospital(Hospital hospital);
	
	public void cancelHospital(String hospitalId);

}
