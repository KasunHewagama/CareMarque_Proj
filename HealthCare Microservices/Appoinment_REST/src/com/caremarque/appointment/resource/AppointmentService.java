package com.caremarque.appointment.resource;

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

@Path("/Appointment")
public class AppointmentService {
	
	IAppointmentService as = new AppointmentServiceImpl();
	Appointment appointment = new Appointment();
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createAppointment(
			@FormParam("patientId") String patientId,
			@FormParam("patientName") String patientName,
			@FormParam("phone") String phone,
			@FormParam("doctorName") String doctorName,
			@FormParam("specialization") String specialization,
			@FormParam("hospitalId") String hospitalId,
			@FormParam("hospitalName") String hospitalName,
			@FormParam("appointmentDate") String appointmentDate,
			@FormParam("appointmentTime") String appointmentTime,
			@FormParam("lastUpdateDate") String lastUpdateDate,
			@FormParam("lastUpdateTime") String lastUpdateTime,
			@FormParam("appointmentStatus") String appointmentStatus) 
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
	
	@GET
	@Path("/{appointmentId}")
	@Produces(MediaType.TEXT_HTML)
	public String getAppointment(@PathParam("appointmentId") String appointmentId) {
		
		return as.getAppointment(appointmentId);
		
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
//		String lastUpdateDate = appointmentObject.get("lastUpdateDate").getAsString();
//		String lastUpdateTime = appointmentObject.get("lastUpdateTime").getAsString();
//		String appointmentStatus = appointmentObject.get("appointmentStatus").getAsString();
		
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
//		appointment.setLastUpdateDate(lastUpdateDate);
//		appointment.setLastUpdateTime(lastUpdateTime);
//		appointment.setAppointmentStatus(appointmentStatus);
		
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
	
	//To Connect with payment resource
//	@GET
//	@Path("/createPayment")
//	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
//	@Produces(MediaType.TEXT_PLAIN)
//	public String createPayment(@FormParam ("patientId") String patientId,
//							@FormParam("patientName") String patientName,
//							@FormParam("appointmentId") String appointmentId,
//							@FormParam("doctorId") String doctorId,
//							@FormParam("hospitalId") String hospitalId,
//							@FormParam("doctorCharges") double doctorCharges,
//							@FormParam("hospitalCharges") double hospitalCharges,
//							@FormParam("paymentStatus") String paymentStatus) {
//		
//		PaymentResource pr = new PaymentResource(); 
//		
//		String output = pr.createPayment(patientId,patientName, appointmentId, doctorId,hospitalId, doctorCharges, hospitalCharges, paymentStatus);
//		
//		return output;
//		
//	}

}
