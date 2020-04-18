package com.caremarque.payment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.payment.model.Payment;
import com.caremarque.payment.model.PaymentAuthentication;
import com.caremarque.payment.utils.CommonUtils;
import com.caremarque.payment.utils.Constants;
import com.caremarque.payment.utils.DBConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;



public class PaymentServiceImpl implements IPaymentService {
	
	//this object is for logging 
	public static final Logger log = Logger.getLogger(PaymentServiceImpl.class.getName());
	
	public static Connection connecton;
	
	public static Statement statement;
	
	private static PreparedStatement preparedstatement;

	@Override
	public String createPayement(Payment p) {
		
		String output = null;
		boolean validate = false;
		
		//Here we call the generatePaymentIDs method to auto generate a paymentID
		//To do that we pass the existing paymentid set as an arraylist
		String paymentId = CommonUtils.generatePaymentIDs(getPaymentIDs());
		
		List<PaymentAuthentication> pAuthList = getDetails();
		
		try {
			
				connecton = DBConnection.getDBConnection();
				
				if(connecton == null)
				{
					return "Error occured while connecting to the database for inserting";
				}
				
				String query = "INSERT INTO PAYMENTS ("
						+ "paymentId, patientId, patientName, appointmentId, doctorId, hospitalId, paymentDate, paymentTime,"
						+ " doctorCharges, hospitalCharges, totalAmount, telPhone, email, paymentStatus)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				preparedstatement = connecton.prepareStatement(query);
				
			/*
			 * java.util.Date date = new Date() DateTimeFormatter formatter = new DateTimeFormatter("dd/MM/yyyy HH:mm:ss");
			 *
			 */
				for (PaymentAuthentication paymentAuthentication : pAuthList) {
					System.out.println("PAUTH:1 " + paymentAuthentication.getCardNo());
					System.out.println("PAUTH:2 " + p.getCardNo());
					System.out.println("PAUTH:1 " + paymentAuthentication.getPassCode());
					System.out.println("PAUTH:2 " + p.getPassCode());
					if(p.getCardNo().equals(paymentAuthentication.getCardNo()) && paymentAuthentication.getPassCode().equals(p.getPassCode())) {
					
							validate = true;
							System.out.println("VALIDATE: " + validate);
							break;
						}
					
				}
				
				if(validate == true) {
					p.setPaymentId(paymentId);
					preparedstatement.setString(Constants.COLUMN_INDEX_ONE, p.getPaymentId());
					preparedstatement.setString(Constants.COLUMN_INDEX_TWO, p.getPatientId());
					preparedstatement.setString(Constants.COLUMN_INDEX_THREE, p.getPatientName());
					preparedstatement.setString(Constants.COLUMN_INDEX_FOUR, p.getAppointmentId());
					preparedstatement.setString(Constants.COLUMN_INDEX_FIVE, p.getDoctorId());
					preparedstatement.setString(Constants.COLUMN_INDEX_SIX, p.getHospitalId());
					preparedstatement.setString(Constants.COLUMN_INDEX_SEVEN, LocalDate.now().toString());
					preparedstatement.setString(Constants.COLUMN_INDEX_EIGHT, LocalTime.now().toString());
					preparedstatement.setDouble(Constants.COLUMN_INDEX_NINE, p.getDoctorCharges());
					preparedstatement.setDouble(Constants.COLUMN_INDEX_TEN, p.getHospitalCharges());
					preparedstatement.setDouble(Constants.COLUMN_INDEX_ELEVEN, p.getDoctorCharges() +  p.getHospitalCharges());
					preparedstatement.setString(Constants.COLUMN_INDEX_TWELVE, p.getTelPhone());
					preparedstatement.setString(Constants.COLUMN_INDEX_THIRTEEN, p.getEmail());
					preparedstatement.setString(Constants.COLUMN_INDEX_FOURTEEN, p.getPaymentStatus());
					preparedstatement.execute();
					
					output = "Data inserted successfully!";
				} else {
					output = "Authentication ERROR!!!";
					System.out.println("Authentication ERROR!!!");
				}

		} catch (Exception e) {
			
			log.log(Level.SEVERE, e.getMessage());
			output = "Error while inserting data to payments table";
		
		} finally {
			
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	@Override
	public String getPaymentById(String paymentId) {

		String output = null;
		ArrayList<Payment> arrayList = new ArrayList<Payment>();

		
		try {
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM PAYMENTS WHERE paymentId = '"+ paymentId + "'";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			
			output = "<table border=\"1\"> "
					+ "<tr>"
					+ "<th>PaymentId</th> "
					+ "<th>PatientId</th> "
					+ "<th>PatientName</th> "
					+ "<th>AppointmentId</th> "
					+ "<th>DoctorId</th> "
					+ "<th>HospitalId</th> "
					+ "<th>PaymentDate</th> "
					+ "<th>PaymentDate</th> "
					+ "<th>DoctorCharges</th> "
					+ "<th>HospitalCharges</th> "
					+ "<th>TotalAmount</th> "
					+ "<th>Telephone No</th> "
					+ "<th>Email</th> "
					+ "<th>PaymentStatus</th>"
					+ "</tr>";  
			
			while(resultset.next()) {
				Payment payment = new Payment();
				payment.setPaymentId(resultset.getString(Constants.COLUMN_INDEX_ONE));
				payment.setPatientId(resultset.getString(Constants.COLUMN_INDEX_TWO));
				payment.setPatientName(resultset.getString(Constants.COLUMN_INDEX_THREE));
				payment.setAppointmentId(resultset.getString(Constants.COLUMN_INDEX_FOUR));
				payment.setDoctorId(resultset.getString(Constants.COLUMN_INDEX_FIVE));
				payment.setHospitalId(resultset.getString(Constants.COLUMN_INDEX_SIX));
				payment.setPaymentDate(resultset.getString(Constants.COLUMN_INDEX_SEVEN));
				payment.setPaymentTime(resultset.getString(Constants.COLUMN_INDEX_EIGHT));
				payment.setDoctorCharges(resultset.getDouble(Constants.COLUMN_INDEX_NINE));
				payment.setHospitalCharges(resultset.getDouble(Constants.COLUMN_INDEX_TEN));
				payment.setTotalAmount(resultset.getDouble(Constants.COLUMN_INDEX_ELEVEN));
				payment.setTelPhone(resultset.getString(Constants.COLUMN_INDEX_TWELVE));
				payment.setEmail(resultset.getString(Constants.COLUMN_INDEX_THIRTEEN));
				payment.setPaymentStatus(resultset.getString(Constants.COLUMN_INDEX_FOURTEEN));
				arrayList.add(payment);
				
				
				 output += "<tr><td>" + resultset.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FOUR) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FIVE) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_SIX) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_NINE) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_TEN) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_ELEVEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_TWELVE) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_THIRTEEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FOURTEEN) + "</td></tr>"; 
				
				
				System.out.println("Data Retreived From DB");
			}
			
			output += "</table>"; 
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	@Override
	public String getPayments() {

		ArrayList<Payment> arrayList = new ArrayList<Payment>();
		String output = null;
		
		try {
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM PAYMENTS";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			DateFormat inputFormatter = new SimpleDateFormat("dd/MM/yyyy");
			
			output = "<table border=\"1\"> "
					+ "<tr>"
					+ "<th>PaymentId</th> "
					+ "<th>PatientId</th> "
					+ "<th>PatientName</th> "
					+ "<th>AppointmentId</th> "
					+ "<th>DoctorId</th> "
					+ "<th>HospitalId</th> "
					+ "<th>PaymentDate</th> "
					+ "<th>PaymentDate</th> "
					+ "<th>DoctorCharges</th> "
					+ "<th>HospitalCharges</th> "
					+ "<th>TotalAmount</th> "
					+ "<th>Telephone No</th> "
					+ "<th>Email</th> "
					+ "<th>PaymentStatus</th>"
					+ "</tr>"; 
			
			while(resultset.next()) {
				Payment payment = new Payment();
				payment.setPaymentId(resultset.getString(Constants.COLUMN_INDEX_ONE));
				payment.setPatientId(resultset.getString(Constants.COLUMN_INDEX_TWO));
				payment.setPatientName(resultset.getString(Constants.COLUMN_INDEX_THREE));
				payment.setAppointmentId(resultset.getString(Constants.COLUMN_INDEX_FOUR));
				payment.setDoctorId(resultset.getString(Constants.COLUMN_INDEX_FIVE));
				payment.setHospitalId(resultset.getString(Constants.COLUMN_INDEX_SIX));
				payment.setPaymentDate(resultset.getString(Constants.COLUMN_INDEX_SEVEN));
				payment.setPaymentTime(resultset.getString(Constants.COLUMN_INDEX_EIGHT));
				payment.setDoctorCharges(resultset.getDouble(Constants.COLUMN_INDEX_NINE));
				payment.setHospitalCharges(resultset.getDouble(Constants.COLUMN_INDEX_TEN));
				payment.setTotalAmount(resultset.getDouble(Constants.COLUMN_INDEX_ELEVEN));
				payment.setTelPhone(resultset.getString(Constants.COLUMN_INDEX_TWELVE));
				payment.setEmail(resultset.getString(Constants.COLUMN_INDEX_THIRTEEN));
				payment.setPaymentStatus(resultset.getString(Constants.COLUMN_INDEX_FOURTEEN));
				arrayList.add(payment);
				
				
				 output += "<tr><td>" + resultset.getString(Constants.COLUMN_INDEX_ONE) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_TWO) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_THREE) + "</td>";
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FOUR) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FIVE) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_SIX) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_SEVEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_EIGHT) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_NINE) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_TEN) + "</td>"; 
				 output += "<td>" + resultset.getDouble(Constants.COLUMN_INDEX_ELEVEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_TWELVE) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_THIRTEEN) + "</td>"; 
				 output += "<td>" + resultset.getString(Constants.COLUMN_INDEX_FOURTEEN) + "</td></tr>"; 
				
			
				System.err.println("Data Retreived From DB");
			}
			
			output += "</table>"; 
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	@Override
	public String updatePayment(String paymentid, String telPhone, String email) {
		
		String output = "";
		
		try {
			connecton = DBConnection.getDBConnection();
			
			if(connecton == null) {
				return "Error while connecting to the database for deleteing";
			}
			
			String query = "UPDATE payments "
					+ "SET telPhone = ?, email = ? "
					+ "WHERE paymentId = ?";
			
			preparedstatement = connecton.prepareStatement(query);
			preparedstatement.setString(Constants.COLUMN_INDEX_ONE, telPhone);
			preparedstatement.setString(Constants.COLUMN_INDEX_TWO, email);
			preparedstatement.setString(Constants.COLUMN_INDEX_THREE, paymentid);
			preparedstatement.execute();
			
			output = "Payment ID: " + paymentid + " was updated";

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			output = "Error occured while updating";
		
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	@Override
	public String cancelPayment(String paymentId) {
		
		String output = "";
		String status = "cancel";
		
		try {
			connecton = DBConnection.getDBConnection();
			
			if(connecton == null) {
				return "Error while connecting to the database for deleteing";
			}
			
			String query = "UPDATE payments "
					+ "SET paymentStatus = ? "
					+ "WHERE paymentId = ?";
			
			preparedstatement = connecton.prepareStatement(query);
			preparedstatement.setString(Constants.COLUMN_INDEX_ONE, status);
			preparedstatement.setString(Constants.COLUMN_INDEX_TWO, paymentId);
			preparedstatement.execute();
			
			output = paymentId + " status changed to cancel!";

		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
		return output;
	}

	//This method get all the existing paymentids and put them into a arraylist 
	@Override
	public ArrayList<String> getPaymentIDs() {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		
		try {
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT PAYMENTS.PAYMENTID FROM PAYMENTS";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				arrayList.add(resultset.getString(Constants.COLUMN_INDEX_ONE));
				
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
			
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
	
	public List<PaymentAuthentication> getDetails() {
		
		List<PaymentAuthentication> pAuthList = new ArrayList<PaymentAuthentication>();
		
		try {

			Client client = Client.create();

			//for the current moment all the services has been configured in a one server, but it is also works for separate services
			WebResource webResource = client
			   .resource("http://localhost:8088/PaymentAuth_REST/paymentAuthService/PaymentAuthentication/getAuthDetails");
			
			//this one is for if we run this service in a separate servier which has port number: 8090
//			WebResource webResource = client
//					   .resource("http://localhost:8088/PaymentAuth_REST/paymentAuthService/PaymentAuthentication/getAuthDetails");

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			Gson gson = new Gson();
			JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("paymentAuthentication");
			List<PaymentAuthentication> listObj = gson.fromJson(list, new TypeToken<List<PaymentAuthentication>>() {}.getType());
		    System.out.println(listObj.size());
		    
		    for (PaymentAuthentication paymentAuthentication : listObj) {
		    	System.out.println(paymentAuthentication);
		    	pAuthList.add(paymentAuthentication);
		    	
			}
		    
		    for (PaymentAuthentication paymentAuthentication : pAuthList) {
		    	 System.out.println("ID: " + paymentAuthentication.getAuthId());
		    	 System.out.println("CODE: " + paymentAuthentication.getCardNo());
			}
		   
		    
		    System.out.println(listObj.get(0).getAuthId());
		    System.out.println(listObj.get(0).getCardNo());
		    System.out.println(listObj.get(0).getExpDate());
		    
//			ObjectMapper mapper = new ObjectMapper();
//			List<PaymentAuthentication> pAuth = mapper.readValue(output, new TypeReference<List<PaymentAuthentication>>() {});
			
//			System.out.println("Output from Server .... \n");
//			System.out.println(pAuth);
//			System.out.println(pAuth.size());
//			System.out.println(pAuth.get(0).getAuthId());

		  } catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());

		  }
			
		return pAuthList;
	}
	
	public double getHospitalCharges(String hospitalId) {
		
		List hospitalList = new ArrayList();
		double hospitalCharges = 0;
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:8088/Hospital_REST/myService/Hospital/" + hospitalId);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			Gson gson = new Gson();
			JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("hospital");
			List listObj = gson.fromJson(list, new TypeToken<List>() {}.getType());
		    System.out.println(listObj.size());
		    
		    //hospitalCharges = listObj.get(0).getC


		  } catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());

		  }
		return hospitalCharges;
	}

}
