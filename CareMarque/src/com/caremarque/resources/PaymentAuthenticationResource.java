package com.caremarque.resources;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.model.PaymentAuthentication;
import com.caremarque.service.paymentAuthentication.PaymentAuthService;

@Path("/PaymentAuthentication")
public class PaymentAuthenticationResource {

	PaymentAuthService pAuth = new PaymentAuthService();
	
	@GET
	@Path("/")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentAuthentication> getAuthenticationDetails() {
		
		System.out.println(pAuth.creditCardAuthFromDB().toString());
		return pAuth.creditCardAuthFromDB();
	}
}
