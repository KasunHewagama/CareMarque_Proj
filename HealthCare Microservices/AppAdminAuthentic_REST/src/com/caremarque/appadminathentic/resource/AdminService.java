package com.caremarque.appadminathentic.resource;


import javax.websocket.server.PathParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.caremarque.appadminathentic.model.Admin;
import com.caremarque.appadminathentic.service.AdminServiceImpl;
import com.caremarque.appadminathentic.service.IAdminService;

import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


@Path("/Admin")
public class AdminService {
	IAdminService as = new AdminServiceImpl();
	Admin admin = new Admin();
	
	
	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	
	public String createAdmin(
			@FormParam("username") String username,
			@FormParam("email") String email,
			@FormParam("password") String password) {
		admin.setUsername(username);
		admin.setEmail(email);
		admin.setPassword(password);
		 String output = as.createAdmin(admin);
		 return output;
	}
	

}
