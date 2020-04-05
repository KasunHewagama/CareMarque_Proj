package com.caremarque.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Payment")
public class PaymentResource {

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getPayments() {
		return "Payment Resource Called";
	}
}
