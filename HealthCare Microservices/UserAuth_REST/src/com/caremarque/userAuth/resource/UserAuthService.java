package com.caremarque.userAuth.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.userAuth.model.PatientAuthentication;
import com.caremarque.userAuth.service.UserAuthServiceImpl;

@Path("/UserAuthentication")
public class UserAuthService {
	
	UserAuthServiceImpl userAuth = new UserAuthServiceImpl();
	
	@GET
	@Path("/getPatientAuth")
	@Produces(MediaType.APPLICATION_JSON)
	public List<PatientAuthentication> checkPatientAuthDetails(){
		
		System.out.println("resource : " + userAuth.checkPatientDetails().toString());
		return userAuth.checkPatientDetails();
		
		
	}

}
