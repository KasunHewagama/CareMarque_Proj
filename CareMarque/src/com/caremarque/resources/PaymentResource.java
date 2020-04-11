package com.caremarque.resources;

import java.sql.Date;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.model.Payment;
import com.caremarque.service.payment.IPaymentService;
import com.caremarque.service.payment.PaymentService;

@Path("/Payment")
public class PaymentResource {
	
	IPaymentService ps = new PaymentService(); 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPayment(@FormParam("paymentId") String paymentId,
								@FormParam("patientId") String patientId,
								@FormParam("patientName") String patientName,
								@FormParam("appointmentId") String appointmentId,
								@FormParam("doctorId") String doctorId,
								@FormParam("hospitalId") String hospitalId,
								@FormParam("paymentDate") Date paymentDate,
								@FormParam("doctorCharges") double doctorCharges,
								@FormParam("hospitalCharges") double hospitalCharges,
								@FormParam("paymentStatus") String paymentStatus) {
		
		System.out.println("CREATE PAYMENT");
		//create payment object
		Payment payment = new Payment();
		
		payment.setPaymentId(paymentId);
		payment.setPatientId(patientId);
		payment.setPatientName(patientName);
		payment.setAppointmentId(appointmentId);
		payment.setDoctorId(doctorId);
		payment.setHospitalId(hospitalId);
		payment.setPaymentDate(paymentDate);
		payment.setDoctorCharges(doctorCharges);
		payment.setHospitalCharges(hospitalCharges);
		payment.setPaymentStatus(paymentStatus);
		
		//pass object to the service implementation class
		String output = ps.createPayement(payment); 
		
		return output;
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPayments() {
		return "Payment Resource Called";
	}
	
}
