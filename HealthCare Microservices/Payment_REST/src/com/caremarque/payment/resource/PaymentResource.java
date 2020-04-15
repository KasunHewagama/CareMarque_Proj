package com.caremarque.payment.resource;


import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.crypto.Data;

import com.caremarque.payment.model.Payment;
import com.caremarque.payment.model.PaymentAuthentication;
import com.caremarque.payment.service.IPaymentService;
import com.caremarque.payment.service.PaymentService;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Payment")
public class PaymentResource {
	
	PaymentService ps = new PaymentService(); 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPayment(@FormParam("patientId") String patientId,
								@FormParam("patientName") String patientName,
								@FormParam("appointmentId") String appointmentId,
								@FormParam("doctorId") String doctorId,
								@FormParam("hospitalId") String hospitalId,
								@FormParam("doctorCharges") double doctorCharges,
								@FormParam("hospitalCharges") double hospitalCharges,
								@FormParam("paymentStatus") String paymentStatus) {
		
		System.out.println("CREATE PAYMENT");
		//create payment object
		Payment payment = new Payment();
		
		payment.setPatientId(patientId);
		payment.setPatientName(patientName);
		payment.setAppointmentId(appointmentId);
		payment.setDoctorId(doctorId);
		payment.setHospitalId(hospitalId);
		payment.setDoctorCharges(doctorCharges);
		payment.setHospitalCharges(hospitalCharges);
		payment.setPaymentStatus(paymentStatus);
		
		//pass object to the service implementation class
		String output = ps.createPayement(payment); 
		
		return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPayments() {
		
		return ps.getPayments();
	}
	
	@GET
	@Path("/{paymentId}")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentById(@PathParam("paymentId") String paymentId) {
		
		return ps.getPaymentById(paymentId);
	}
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deletePayment(String paymentId) {
		
		return null;
	}
	
	@GET
	@Path("/testget")
	@Produces(MediaType.TEXT_PLAIN)
	public String testValue() {
		String output = ps.getDetails();
		return output;
		
	}
	
}
