package com.caremarque.patientAuth.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.patientAuth.model.PatientAuthentication;
import com.caremarque.patientAuth.service.PatientAuthServiceImpl;

@Path("/PatientAuthentication")
public class PatientAuthService {

	PatientAuthServiceImpl patientAuth = new PatientAuthServiceImpl();

	@GET
	@Path("/getPatientAuth")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientAuthentication> checkPatientAuthDetails() {

		System.out.println("resource : " + patientAuth.checkPatientDetails().toString());
		return patientAuth.checkPatientDetails();

	}

	@GET
	@Path("/getPatientForAppointment")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientAuthentication> checkPatientDetailsForAppointment() {

		return patientAuth.checkPatientDetailsForAppointment();
	}

}
