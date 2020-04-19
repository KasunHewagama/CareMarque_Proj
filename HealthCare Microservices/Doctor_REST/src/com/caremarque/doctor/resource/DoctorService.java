package com.caremarque.doctor.resource;

//import javax.validation.constraints.NotNull;
//import javax.validation.constraints.Pattern;
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

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.service.DoctorServiceImpl;
import com.caremarque.doctor.service.IDoctorService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

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
			
			//@NotNull(message = "Gender cannot be empty..!") @Pattern(regexp = "/^[a-zA-Z]+$/", message = "Gender should have alphabets only") 
			@FormParam("gender") String gender,
			
			@FormParam("specialization") String specialization,
			@FormParam("phone") String phone,
			@FormParam("email") String email,
			@FormParam("doctorCharges") double doctorCharges,
			@FormParam("password") String password,
			@FormParam("confirmPassword") String confirmPassword)
	
	{
		System.out.println("CREATE Doctor...!");
		//create appointment object
		
		//Doctor doctor = new Doctor();
		doctor.setDoctorId(doctorId);
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setRegNo(regNo);
		doctor.setGender(gender);
		doctor.setSpecialization(specialization);
		doctor.setPhone(phone);
		doctor.setEmail(email);
		doctor.setDoctorCharges(doctorCharges);
		doctor.setPassword(password);
		doctor.setConfirmPassword(confirmPassword);
		
		//pass object to the service implementation class
		String output = ab.createDoctor(doctor);
		
		return output;
		
	}
	
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getDoctors() {
		return ab.getDoctors();
	}
	
	@GET
	@Path("/{doctorId}")
	@Produces(MediaType.TEXT_HTML)
	public String getDoctor(@PathParam("doctorId") String id) {
		return ab.getDoctor(id);
	}
	
	
	
	@PUT
	@Path("/update")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	

	public String updateDoctor(String doctorData) {
		
		//com.google.gson.JsonParser parser = new com.google.gson.JsonParser();
		
		JsonObject dObject = new JsonParser().parse(doctorData).getAsJsonObject();
		
		String doctorId = dObject.get("doctorId").getAsString();
		String firstName = dObject.get("firstName").getAsString();
		String lastName = dObject.get("lastName").getAsString();
		String regNo = dObject.get("regNo").getAsString();
		String gender = dObject.get("gender").getAsString();
		String specialization = dObject.get("specialization").getAsString();
		String phone = dObject.get("phone").getAsString();
		String email = dObject.get("email").getAsString();
		double doctorCharges = dObject.get("doctorCharges").getAsDouble();
		String password = dObject.get("password").getAsString();
		String confirmPassword = dObject.get("confirmPassword").getAsString();
		
		//doctor.setDoctorId(Integer.parseInt(doctorId));
		doctor.setDoctorId(doctorId);
		doctor.setFirstName(firstName);
		doctor.setLastName(lastName);
		doctor.setRegNo(regNo);
		doctor.setGender(gender);
		doctor.setSpecialization(specialization);
		doctor.setPhone(phone);
		doctor.setEmail(email);
		doctor.setDoctorCharges(doctorCharges);
		doctor.setPassword(password);
		doctor.setConfirmPassword(confirmPassword);
		
		String output = ab.updateDoctor(doctorId,doctor);
		
		return output;
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String deleteDoctor( String doctorData) {
		Document doc = Jsoup.parse(doctorData,"",Parser.xmlParser());
		
		String doctorId = doc.select("doctorId").text();
		
		String output = ab.cancelDoctor(doctorId);
		return output;
		
	}
	

	
	@GET
	@Path("/appoint")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_HTML)
	public String getAllAppointments(String doctorData) {
		Document doc = Jsoup.parse(doctorData,"",Parser.xmlParser());
		
		String doctorId = doc.select("doctorId").text();
		
		String output = ab.getAllAppointments(doctorId);
		return output;
		
	} 
	

	@POST
	@Path("/login")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String login(
			@FormParam("userName") String email,
			@FormParam("password") String password,
			@FormParam("type") String type) {
		
		doctor.setEmail(email);
		doctor.setPassword(password);
		doctor.setType(type);
		
		String output = ab.login(doctor);
		
		return output;
	}
	
}
