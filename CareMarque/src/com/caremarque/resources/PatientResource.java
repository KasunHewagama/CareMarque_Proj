package com.caremarque.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.model.Patient;
import com.caremarque.service.patient.PatientService;


@Path("/Patients")
public class PatientResource {

PatientService patientObj = new PatientService();
Patient patient;

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerPatient( @FormParam("patientId") String patientId,
								  @FormParam("firstName") String firstName,
								  @FormParam("lastName") String lastName,
								  @FormParam("gender") String gender,
								  @FormParam("NIC") String NIC,
								  @FormParam("DOB") String DOB,
								  @FormParam("email") String email,
								  @FormParam("phone") String phone,
								  @FormParam("bloodGroup") String bloodGroup,
								  @FormParam("allergies") String allergies,
								  @FormParam("password") String password,
								  @FormParam("cPassword") String cPassword) 
	{
		String output = patientObj.registerPatient(patientId, firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword);
		return output;
	}
	
//	@GET
//	@Path("/")
//	@Produces(MediaType.TEXT_HTML)
//	public String getPatient() {
//		
//		return patientObj.getPatient();
//		
//	}
//	
	
}
