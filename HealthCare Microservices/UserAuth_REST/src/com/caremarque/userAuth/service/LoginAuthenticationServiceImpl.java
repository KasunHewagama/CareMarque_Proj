package com.caremarque.userAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.jsoup.helper.Validate;

import com.caremarque.userAuth.model.Doctor;
import com.caremarque.userAuth.model.Patient;
import com.caremarque.userAuth.model.loginAuthentication;
import com.caremarque.userAuth.utils.DBConnection;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

public class LoginAuthenticationServiceImpl {

	private static final Logger logger = Logger.getLogger(LoginAuthenticationServiceImpl.class.getName());

	private static Connection con;
	
	private static PreparedStatement preparedStmt;
	
	private static Statement statement;
	
	private static Patient patient;
	
	private static Doctor doctor;
	
	
	public boolean authenticate(String userName, String password, String type){
		
		ResultSet rs = null;
		String email = null;
		String pwd = null;
		
		try {
			
			con = DBConnection.getDBConnection();
			
			if(type == "Patient" || type == "patient") {
				
				String query = "SELECT email, password FROM patient";
				
				statement = con.createStatement();
				rs = statement.executeQuery(query);
				
				while(rs.next()) {
					
					 email = rs.getString("email");
					 pwd = rs.getString("password");
				}
				
				if(email == userName && pwd == password) {
					return true;
				}
				else {
					return false;
				}
							
			}else if(type == "Doctor" || type == "doctor") {
				
				String query = "SELECT email, password FROM doctor";
				
				statement = con.createStatement();
				rs = statement.executeQuery(query);
				
				while(rs.next()) {
					
					 email = rs.getString("email");
					 pwd = rs.getString("password");
				}
				
				if(email == userName && pwd == password) {
					return true;
				}
				else {
					return false;
				}

			}
			
		}catch (Exception e) {
			
		}
		return false;
		
		
	}
	
	public String issueToken(String userName) {
		return userName;
		
	}
	
	public String loginValidation(String userName, String password, String type) {
		
//		List<Patient> hospitalList = new ArrayList();
		 boolean validate = false;
		String output = "";
		
		try {

			Client client = Client.create();

			WebResource webResource = client
			   .resource("http://localhost:9090/Patient_REST/patientService/Patient/fromJson");

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			output = response.getEntity(String.class);

			Gson gson = new Gson();
			if(type.equals("Doctor")) {
				JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("doctor");
				List<Doctor> listObj = gson.fromJson(list, new TypeToken<List<Doctor>>() {}.getType());
			    System.out.println(listObj.size());
			    
			   
			    
			    for (Doctor doctor : listObj) {
					if(doctor.getEmail().equals(userName) && doctor.getPassword().equals(password)) {
						validate = true;
						break;
					}
				}
			}
			else {
				JsonElement list = new JsonParser().parse(output).getAsJsonObject().get("patients");
				List<Patient> listObj = gson.fromJson(list, new TypeToken<List<Patient>>() {}.getType());
			    System.out.println(listObj.size());
			    
			    
			    for (Patient patient : listObj) {
					if(patient.getEmail().equals(userName) && patient.getPassword().equals(password)) {
						validate = true;
						break;
					}
				}
			}
		    //hospitalCharges = listObj.get(0).getC
			
			if(validate == true) {
				output = "Login Successfull!";
			}
			else {
				output = "Login Fail!";
			}
		  } catch (Exception e) {
			//Log.log(Level.SEVERE, e.getMessage());
			  System.out.println(e.getMessage());
		  }
		return output;
	}


}
