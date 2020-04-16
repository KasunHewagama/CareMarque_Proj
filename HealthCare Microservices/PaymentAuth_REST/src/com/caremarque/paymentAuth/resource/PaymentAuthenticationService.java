package com.caremarque.paymentAuth.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.paymentAuth.model.PaymentAuthentication;
import com.caremarque.paymentAuth.service.PaymentAuthServiceImpl;

@Path("/PaymentAuthentication")
public class PaymentAuthenticationService {

	PaymentAuthServiceImpl pAuth = new PaymentAuthServiceImpl();
	
	@GET
	@Path("/getAuthDetails")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PaymentAuthentication> getAuthDetails() {
		
		System.out.println(pAuth.creditCardAuthFromDB().toString());
		return pAuth.creditCardAuthFromDB();
	}
}
