package com.caremarque.payment.resource;

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
import javax.ws.rs.core.Response;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.caremarque.payment.model.Appointment;
import com.caremarque.payment.model.Payment;
import com.caremarque.payment.service.PaymentServiceImpl;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Path("/Payment")
public class PaymentService {
	
	PaymentServiceImpl ps = new PaymentServiceImpl(); 

	@POST
	@Path("/")
	@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	@Produces(MediaType.TEXT_PLAIN)
	public String createPayment(@FormParam("patientId") String patientId,
								@FormParam("patientName") String patientName,
								@FormParam("appointmentId") String appointmentId,
								@FormParam("doctorId") String doctorId,
								@FormParam("hospitalId") String hospitalId,
								@FormParam("doctorCharges") double doctorCharges,
								@FormParam("hospitalCharges") double hospitalCharges,
								@FormParam("paymentStatus") String paymentStatus,
								@FormParam("cardNo") String cardNo,
								@FormParam("expDate") String expDate,
								@FormParam("passCode") String passCode,
								@FormParam("telPhone") String telPhone,
								@FormParam("email") String email) {
		
		System.out.println("CREATE PAYMENT");
		//create payment object
		Payment payment = new Payment();
		
		payment.setPatientId(patientId);
		payment.setPatientName(patientName);
		payment.setAppointmentId(appointmentId);
		payment.setDoctorId(doctorId);
		payment.setHospitalId(hospitalId);
		payment.setDoctorCharges(doctorCharges);
		payment.setHospitalCharges(hospitalCharges);
		payment.setPaymentStatus(paymentStatus);
		payment.setCardNo(cardNo);
		payment.setExpDate(expDate);
		payment.setPassCode(passCode);
		payment.setTelPhone(telPhone);
		payment.setEmail(email);
		
		//pass object to the service implementation class
		String output = ps.createPayement(payment); 
		
		return output;
	}
	
	@POST
	@Path("/fromAppointment")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response createPaymentFromAppointment(Appointment appointment) {
		System.out.println("Hello");
		String result = "Record Taken: " + appointment;
		System.out.println(appointment);
		return Response.status(201).entity(result).build();
		
	}
	
	@GET
	@Path("/")
	@Produces(MediaType.TEXT_HTML)
	public String getPayments() {
		
		return ps.getPayments();
	}
	
	@GET
	@Path("/{paymentId}")
	@Produces(MediaType.TEXT_HTML)
	public String getPaymentById(@PathParam("paymentId") String paymentId) {
		
		return ps.getPaymentById(paymentId);
	}
	
	@PUT
	@Path("/")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String updatePayment(String paymentData) {
		
		JsonObject paymentObj = new JsonParser().parse(paymentData).getAsJsonObject();
		
		String paymentId = paymentObj.get("paymentId").getAsString();
		String telPhone = paymentObj.get("telPhone").getAsString();
		String email = paymentObj.get("email").getAsString();
		
		return ps.updatePayment(paymentId, telPhone, email);
	}
	
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelPayment(String paymentData) {
		
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		
		String paymentId = doc.select("paymentId").text();

		return ps.cancelPayment(paymentId);
	}
	
//	@GET
//	@Path("/testget")
//	@Produces(MediaType.TEXT_PLAIN)
//	public String testValue() {
//		String output = ps.getDetails();
//		return output;
//		
//	}
	
}
