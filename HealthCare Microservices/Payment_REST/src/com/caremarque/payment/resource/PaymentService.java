package com.caremarque.payment.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.parser.Parser;

import com.caremarque.payment.model.Payment;
import com.caremarque.payment.service.PaymentServiceImpl;


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
								@FormParam("passCode") String passCode) {
		
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
		
		//pass object to the service implementation class
		String output = ps.createPayement(payment); 
		
		return output;
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
	
	@DELETE
	@Path("/")
	@Consumes(MediaType.APPLICATION_XML)
	@Produces(MediaType.TEXT_PLAIN)
	public String cancelPayment(String paymentData) {
		
		Document doc = Jsoup.parse(paymentData, "", Parser.xmlParser());
		
		String paymentId = doc.select("paymentId").text();
		
		String output = ps.cancelPayment(paymentId);
		
		return output;
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
