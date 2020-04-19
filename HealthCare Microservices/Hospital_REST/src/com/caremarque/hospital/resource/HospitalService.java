package com.caremarque.hospital.resource;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.caremarque.hospital.model.Hospital;
import com.caremarque.hospital.service.HospitalServiceImpl;
import com.caremarque.hospital.service.IHospitalService;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

@Path("/Hospital")
public class HospitalService {
	IHospitalService as = new HospitalServiceImpl();
	Hospital hospital = new Hospital();

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createHospital(
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z][0-9]+$/", message = "Use alphabets only")	@FormParam("hospitalName") String hospitalName,
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z][0-9]+$/")		@FormParam("address") String address,
			@NotEmpty	@Pattern(regexp = "/^\\d{10}$/", message = "numbers only") @FormParam("phone") String phone, 
			@NotEmpty	@Pattern(regexp = "/^[a-zA-Z][0-9]+$/") 	@FormParam("regNo") String regNo,
			@NotEmpty	@Pattern(regexp = "(0?[1-9]|[12][0-9]|3[01])/(0?[1-9]|1[012])/((19|20)\\d\\d)") @FormParam("Open_Hours") String Open_Hours, 
			@NotEmpty	@Pattern(regexp = "/^\\d{1,2}:\\d{2}([ap]m)?$/ss") @FormParam("Close_Hours") String Close_Hours,
			@NotEmpty 	@Pattern(regexp = "/^[\\w\\-\\.\\+]+\\@[a-zA-Z0-9\\.\\-]+\\.[a-zA-z0-9]{2,4}$/") @FormParam("email") String email,
			@NotEmpty 	@Pattern(regexp = "/^\\d{10}$/", message = "Numbers only") @FormParam("channelingFee") String channelingFee) {

		System.out.println("Create Hospital...........!");


		
		hospital.setHospitalName(hospitalName);
		hospital.setAddress(address);
		hospital.setPhone(phone);
		hospital.setRegNo(regNo);
		hospital.setOpen_Hours(Open_Hours);
		hospital.setClose_Hours(Close_Hours);
		hospital.setEmail(email);
		hospital.setChannelingFee(channelingFee);

		String result = as.createHospital(hospital);
		return result;

	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getHospitals() {
		return as.getHospitals();
	}

	
	  @GET
	  @Path("/{hospitalId}")
	  @Produces(MediaType.TEXT_HTML)
	  public String getHospital(@PathParam("hospitalId") String hospitalId) {
	  return as.getHospital(hospitalId);
	  
	  }
	 
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_HTML)
	public String putHospital(String hospitalData) {

		JsonObject hosJsonObject = new JsonParser().parse(hospitalData).getAsJsonObject();

		String hospitalId = hosJsonObject.get("hospitalId").getAsString();
		String hospitalName = hosJsonObject.get("hospitalName").getAsString();
		String address = hosJsonObject.get("address").getAsString();
		String phone = hosJsonObject.get("phone").getAsString();
		String regNo = hosJsonObject.get("regNo").getAsString();
		String Open_Hours = hosJsonObject.get("Open_Hours").getAsString();
		String Close_Hours = hosJsonObject.get("Close_Hours").getAsString();
		String email = hosJsonObject.get("email").getAsString();
		String channelingFee= hosJsonObject.get("channelingFee").getAsString();
		
		hospital.setHospitalId(hospitalId);
		hospital.setHospitalName(hospitalName);
		hospital.setAddress(address);
		hospital.setPhone(phone);
		hospital.setRegNo(regNo);
		hospital.setOpen_Hours(Open_Hours);
		hospital.setClose_Hours(Close_Hours);
		hospital.setEmail(email);
		hospital.setChannelingFee(channelingFee);

		String result = as.updateHospital(hospitalId, hospital);
		return result;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String hospitalData) {

		Document document = Jsoup.parse(hospitalData, "", Parser.xmlParser());

		String hospitalId = document.select("hospitalId").text();

		String result = as.DeleteHospital(hospitalId);

		return result;

	}

}
