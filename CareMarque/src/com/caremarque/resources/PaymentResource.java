package com.caremarque.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.model.Payment;
import com.caremarque.service.payment.PaymentService;

@Path("/Payment")
public class PaymentResource {
	
	PaymentService ps = new PaymentService(); 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPayment() {
		Payment payment = new Payment();
		
		return ps.createPayement(payment);
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPayments() {
		return "Payment Resource Called";
	}
	
}
