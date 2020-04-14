package com.caremarque.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.caremarque.model.Patient;
import com.caremarque.service.patient.PatientService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Patients")
public class PatientResource {
	
PatientService patientObj = new PatientService();
Patient patient = new Patient();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String registerPatient(
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
		
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);
		patient.setNIC(NIC);
		patient.setDOB(DOB);
		patient.setEmail(email);
		patient.setPhone(phone);
		patient.setBloodGroup(bloodGroup);
		patient.setAllergy(allergies);
		patient.setPassword(password);
		patient.setConfirmPassword(cPassword);

		String output = patientObj.registerPatient(patient);
		
		return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPatients() {
		
		return patientObj.getPatients();
		
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePatient(String patientData) {
				
		Document doc = Jsoup.parse(patientData, "", Parser.xmlParser());
		
		String patientId = doc.select("patientId").text();
		
		String output = patientObj.deletePatient(patientId);
		
		return output;
		 
	}
	

	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePatientDetails(String patientData) {
	
		JsonObject pObject = new JsonParser().parse(patientData).getAsJsonObject();
		
		String patientId = pObject.get("patientId").getAsString();
		String firstName = pObject.get("firstName").getAsString();
		String lastName = pObject.get("lastName").getAsString();
		String gender = pObject.get("gender").getAsString();
		String NIC =pObject.get("NIC").getAsString();
		String DOB =pObject.get("DOB").getAsString();
		String email =pObject.get("email").getAsString();
		String phone =pObject.get("phone").getAsString();
		String bloodGroup =pObject.get("bloodGroup").getAsString();
		String allergies =pObject.get("allergies").getAsString();
		String password =pObject.get("password").getAsString();
		String cPassword =pObject.get("cPassword").getAsString();
		
		patient.setPatientId(Integer.parseInt(patientId));
		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		patient.setGender(gender);
		patient.setNIC(NIC);
		patient.setDOB(DOB);
		patient.setEmail(email);
		patient.setPhone(phone);
		patient.setBloodGroup(bloodGroup);
		patient.setAllergy(allergies);
		patient.setPassword(password);
		patient.setConfirmPassword(cPassword);
		
		String output = patientObj.updatePatientDetails(patient);
		
		return output;
		
	}
}
