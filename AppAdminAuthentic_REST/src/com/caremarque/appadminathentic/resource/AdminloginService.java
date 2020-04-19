package com.caremarque.appadminathentic.resource;


import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.caremarque.appadminathentic.service.loginServiceImpl;

@Path("/Login")
public class AdminloginService {

	
	loginServiceImpl loginServiceImpl;

	@POST
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.APPLICATION_JSON)
	public Response authenticateUser(@FormParam("userName") String userName,
									 @FormParam("password") String password) {
		
		try {
			String type = null;
			loginServiceImpl.authenticate(userName, password, type);
			
			String token = loginServiceImpl.issueToken(userName);
			
			return Response.ok(token).build();
		
		}catch (Exception e) {

			return Response.status(Response.Status.FORBIDDEN).build();
		}
	}


}
