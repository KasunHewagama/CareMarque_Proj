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

import com.caremarque.model.Appointment;
import com.caremarque.service.appointment.AppointmentService;
import com.caremarque.service.appointment.IAppointmentService;

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
			@FormParam("hospitalName") String hospitalName,
			@FormParam("hospitalId") String hospitalId,
			@FormParam("appointmentDate") String appointmentDate,
			@FormParam("lastUpdateDate") String lastUpdateDate,
			@FormParam("appointmentTime") String appointmentTime,
			@FormParam("lastUpdateTime") String lastUpdateTime,
			@FormParam("specialization") String specialization,
			@FormParam("appointmentStatus") String appointmentStatus) 
	{
		Appointment appointment = new Appointment();
		
		appointment.setPatientId(patientId);
		appointment.setPatientName(patientName);
		appointment.setPhone(phone);
		appointment.setDoctorName(doctorName);
		appointment.setHospitalName(hospitalName);
		appointment.setHospitalId(hospitalId);
		appointment.setAppointmentDate(appointmentDate);
		appointment.setLastUpdateDate(lastUpdateDate);
		appointment.setAppointmentTime(appointmentTime);
		appointment.setLastUpdateTime(lastUpdateTime);
		appointment.setSpecialization(specialization);
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

}
