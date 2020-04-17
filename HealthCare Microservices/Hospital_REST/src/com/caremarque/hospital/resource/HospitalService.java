package com.caremarque.hospital.resource;

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
	public String createHospital(@FormParam("hospitalId") String hospitalId,
								 @FormParam("hospitalName") String hospitalName,
								 @FormParam("address") String address,
								 @FormParam("phone") String phone, 
								 @FormParam("regNo") String regNo,
								 @FormParam("Open_Hours") String Open_Hours, 
								 @FormParam("Close_Hours") String Close_Hours) {

		System.out.println("Create Hospital...........!");

		Hospital hospital = new Hospital();

		hospital.setHospitalId(hospitalId);
		hospital.setHospitalName(hospitalName);
		hospital.setAddress(address);
		hospital.setPhone(phone);
		hospital.setRegNo(regNo);
		hospital.setOpen_Hours(Open_Hours);
		hospital.setClose_Hours(Close_Hours);

		String output = as.createHospital(hospital);
		return output;

	}

	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getHospitals() {
		return as.getHospitals();
	}

	/*
	 * @GET
	 * 
	 * @Path("/{appointmentId}")
	 * 
	 * @Produces(MediaType.TEXT_HTML) public String
	 * getAppointment(@PathParam("appointmentId") String appointmentId) {
	 * 
	 * return as.getAppointment(appointmentId);
	 * 
	 * }
	 */
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

		hospital.setHospitalId(hospitalId);
		hospital.setHospitalName(hospitalName);
		hospital.setAddress(address);
		hospital.setPhone(phone);
		hospital.setRegNo(regNo);
		hospital.setOpen_Hours(Open_Hours);
		hospital.setClose_Hours(Close_Hours);

		String output = as.updateHospital(hospitalId, hospital);
		return output;

	}

	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String deleteHospital(String appointmentData) {

		Document document = Jsoup.parse(appointmentData, "", Parser.xmlParser());

		String hospitalId = document.select("hospitalId").text();

		String output = as.DeleteHospital(hospitalId);

		return output;

	}

}
