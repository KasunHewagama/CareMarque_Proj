package com.caremarque.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.json.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.caremarque.model.Patient;
import com.caremarque.service.patient.PatientService;
import com.mysql.cj.xdevapi.JsonParser;


@Path("/Patients")
public class PatientResource {
	
PatientService patientObj = new PatientService();

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
		Patient patient = new Patient();
		
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
		
		//String output = patientObj.registerPatient(firstName, lastName, gender, NIC, DOB, email, phone, bloodGroup, allergies, password, cPassword);
		//String output = patientObj.registerPatient();
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
	
//	@PUT
//	@Path("/")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String updatePatientDetails(String patientData) {
//		
//		JSONObject patientObject = new JsonParser().parse(patientData).getAsJsonObject();
//	}
	
	
}
