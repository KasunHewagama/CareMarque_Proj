package com.caremarque.appadminathentic.resource;
import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.appadminathentic.model.AdminAuthentication;
import com.caremarque.appadminathentic.service.AdminAuthServiceImpl;




@Path("/AdminrAuthentication")
public class AdminAuthServie {
	
	AdminAuthServiceImpl adminauthImpl = new AdminAuthServiceImpl();
	
	

	@GET
	@Path("/getAdminAuth")
	@Produces(MediaType.APPLICATION_JSON)
	public List<AdminAuthentication> checkAdminAuthDetails(){
		
		System.out.println("resource : " + adminauthImpl.checkAdmindetails().toString());
		return adminauthImpl.checkAdmindetails();
		
		
	}
}
