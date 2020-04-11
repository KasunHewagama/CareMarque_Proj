package com.caremarque.service.hospital;

import java.util.ArrayList;
import com.caremarque.model.Hospital;

public interface IHospitalService {
	
	public String createHospital(Hospital h);
	
	public Hospital getHospital(String hospitalId);
	
	public ArrayList<Hospital> getHospitals();
	
	public Hospital updateHospital(String hospitalid,Hospital id);
	
	public void cancelHospital(String hospitalId);

}
