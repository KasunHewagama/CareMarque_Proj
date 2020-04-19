package com.caremarque.appointment.resource;

import javax.validation.ValidationException;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
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

import com.caremarque.appointment.model.Appointment;
import com.caremarque.appointment.service.AppointmentServiceImpl;
import com.caremarque.appointment.service.IAppointmentService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.sun.jersey.api.client.Client;

@Path("/Appointment")
public class AppointmentService {
	
	IAppointmentService as = new AppointmentServiceImpl();
	AppointmentServiceImpl as2 = new AppointmentServiceImpl();
	Appointment appointment = new Appointment();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createAppointment(
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z] [0-9]+$/")	@FormParam("patientId") String patientId,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@FormParam("patientName") String patientName,
			@NotEmpty	@Pattern(regexp = "/^\\d{10}$/")	@FormParam("phone") String phone,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@FormParam("doctorName") String doctorName,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@FormParam("specialization") String specialization,
			@NotEmpty 	@Pattern(regexp = "/^[a-zA-Z][0-9]+$/")	@FormParam("hospitalId") String hospitalId,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@FormParam("hospitalName") String hospitalName,
			@NotEmpty	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)")	@FormParam("appointmentDate") String appointmentDate,
			@NotEmpty	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)")	@FormParam("appointmentTime") String appointmentTime,
			@NotEmpty	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)")	@FormParam("lastUpdateDate") String lastUpdateDate,
			@NotEmpty	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)")	@FormParam("lastUpdateTime") String lastUpdateTime,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z]+$/", message = "Use only alphabets")	@FormParam("appointmentStatus") String appointmentStatus) 
	{
		
		System.out.println("CREATE Appointment...!");
		//create appointment object
		
		
		appointment.setPatientId(patientId);
		appointment.setPatientName(patientName);
		appointment.setPhone(phone);
		appointment.setDoctorName(doctorName);
		appointment.setSpecialization(specialization);
		appointment.setHospitalId(hospitalId);
		appointment.setHospitalName(hospitalName);
		appointment.setAppointmentDate(appointmentDate);
		appointment.setAppointmentTime(appointmentTime);
		appointment.setLastUpdateDate(lastUpdateDate);
		appointment.setLastUpdateTime(lastUpdateTime);
		appointment.setAppointmentStatus(appointmentStatus);
		
		//pass object to the service implementation class
		String output = as.createAppointment(appointment);
		
		return output;
		
	}
		
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointments() {
		return as.getAppointments();
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updateAppointment(String appointmentData) {
		
		JsonObject appointmentObject = new JsonParser().parse(appointmentData).getAsJsonObject();
		
		String appointmentId = appointmentObject.get("appointmentId").getAsString();
		String patientId = appointmentObject.get("patientId").getAsString();
		String patientName = appointmentObject.get("patientName").getAsString();
		String phone = appointmentObject.get("phone").getAsString();
		String doctorName = appointmentObject.get("doctorName").getAsString();
		String specialization = appointmentObject.get("specialization").getAsString();
		String hospitalId = appointmentObject.get("hospitalId").getAsString();
		String hospitalName = appointmentObject.get("hospitalName").getAsString();
		String appointmentDate = appointmentObject.get("appointmentDate").getAsString();
		String appointmentTime = appointmentObject.get("appointmentTime").getAsString();
		
		appointment.setAppointmentId(appointmentId);
		appointment.setPatientId(patientId);
		appointment.setPatientName(patientName);
		appointment.setPhone(phone);
		appointment.setDoctorName(doctorName);
		appointment.setSpecialization(specialization);
		appointment.setHospitalId(hospitalId);
		appointment.setHospitalName(hospitalName);
		appointment.setAppointmentDate(appointmentDate);
		appointment.setAppointmentTime(appointmentTime);
		
		String output = as.updateAppointment(appointmentId,appointment);
		
		return output;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment(String appointmentData) {
		
		Document document = Jsoup.parse(appointmentData, "", Parser.xmlParser());
		
		String appointmentId= document.select("appointmentId").text();
		
		String output = as.cancelAppointment(appointmentId);
		
		return output;
		
	}
	
	
	@GET
	@Path("/{appointmentId}")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointment(@PathParam("appointmentId") String appointmentId) {
		
		return as.getAppointment(appointmentId);
		
	}
	
	//To Connect with payment resource
	@GET
	@Path("/createPayment/{appointmentId}")
	@Produces(MediaType.TEXT_PLAIN)
	public void createPayment(@PathParam("appointmentId") String appointmentId) {
		as2.createPayment(appointmentId);
		System.out.println("TRIGGERED");
		
	}

}
