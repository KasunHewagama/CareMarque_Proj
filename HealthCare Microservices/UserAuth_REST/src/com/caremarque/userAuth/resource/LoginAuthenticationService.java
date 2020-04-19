package com.caremarque.userAuth.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.caremarque.userAuth.service.LoginAuthenticationServiceImpl;

@Path("/login")
public class LoginAuthenticationService {
	
	LoginAuthenticationServiceImpl loginAuthServiceImpl;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("userName") String userName,
									 @FormParam("password") String password,
									 @FormParam("type") String type) {
		
		try {
			loginAuthServiceImpl.authenticate(userName, password, type);
			
			String token = loginAuthServiceImpl.issueToken(userName);
			
			return Response.ok(token).build();
		
		}catch (Exception e) {

			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}
	
	@POST
	@Consumes
	@Produces(MediaType.TEXT_PLAIN)
	public String loginValidation(@FormParam("userName") String userName,
			 @FormParam("password") String password,
			 @FormParam("type") String type) {
		
		
	return loginAuthServiceImpl.loginValidation(userName, password, type);
	}
}
