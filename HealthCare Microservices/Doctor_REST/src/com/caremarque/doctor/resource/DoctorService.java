package com.caremarque.doctor.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.service.DoctorServiceImpl;
import com.caremarque.doctor.service.IDoctorService;

@Path("/Doctor")
public class DoctorService {

	IDoctorService ab = new DoctorServiceImpl();
	Doctor doctor = new Doctor();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String createDoctor(
			@FormParam("doctorId") String doctorId,
			@FormParam("firstName") String firstName,
			@FormParam("lastName") String lastName,
			@FormParam("regNo") String regNo,
			@FormParam("gender") String gender,
			@FormParam("specialization") String specialization,
			@FormParam("phone") String phone,
			@FormParam("email") String email,
			@FormParam("password") String password,
			@FormParam("confirmPassword") String confirmPassword)
	
	{
		//Doctor doctor = new Doctor();
		doctor.setDoctorId(doctorId);
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setRegNo(regNo);
		doctor.setGender(gender);
		doctor.setSpecialization(specialization);
		doctor.setPhone(phone);
		doctor.setEmail(email);
		doctor.setPassword(password);
		doctor.setConfirmPassword(confirmPassword);
		
		String output = ab.createDoctor(doctor);
		
		return output;
		
	}
	
}
