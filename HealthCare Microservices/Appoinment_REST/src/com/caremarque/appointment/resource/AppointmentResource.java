package com.caremarque.appointment.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.appointment.model.Appointment;
import com.caremarque.appointment.service.AppointmentService;
import com.caremarque.appointment.service.IAppointmentService;

@Path("/Appointment")
public class AppointmentResource {
	
	IAppointmentService as = new AppointmentService();
	
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
		Appointment appointment = new Appointment();
		
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
		
		String output = as.createAppointment(appointment);
		
		return output;
		
	}
		
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAppointments() {
		return as.getAppointments();
	}
	
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String putAppointment() {
		return null;
		
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteAppointment() {
		return null;
		
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
