package com.caremarque.hospital.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.hospital.model.Hospital;
import com.caremarque.hospital.service.HospitalService;
import com.caremarque.hospital.service.IHospitalService;

@Path("/Hospital")
public class HospitalResource {
	IHospitalService as = new HospitalService();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createHospitalProfile(
			@FormParam("hospitalId") String hospitalId,
			@FormParam("hospitalName") String hospitalName, 
			@FormParam("phone") String phone,
			@FormParam("regNo") String regNo, 
			@FormParam("address") String address,
			@FormParam("Open_Hours") String Open_Hours, 
			@FormParam("Close_Hours") String Close_Hours) {

		System.out.println("Create Hospital Profile");

		Hospital hospital = new Hospital();
		hospital.setHospitalId(hospitalId);
		hospital.setHospitalName(hospitalName);
		hospital.setHospitalId(phone);
		hospital.setRegNo(regNo);
		hospital.setAddress(address);
		hospital.setOpen_Hours(Open_Hours);
		hospital.setClose_Hours(Close_Hours);

		String Hos_obj = as.createHospital(hospital);
		return Hos_obj;

	}

	
}