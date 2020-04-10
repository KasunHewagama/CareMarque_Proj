package com.caremarque.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Hospital")
public class HospitalResource {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	public String getAppointments() {
		// TODO Auto-generated constructor stub
		return "Hospital Resource Called...!";
	}

}
