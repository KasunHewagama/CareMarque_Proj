package com;


import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;



@Path("/Test")
public class Test {
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_PLAIN)
	
	public String Text()
	{
		System.out.println("resultss");
		return "Hello World!!!";
	}

}