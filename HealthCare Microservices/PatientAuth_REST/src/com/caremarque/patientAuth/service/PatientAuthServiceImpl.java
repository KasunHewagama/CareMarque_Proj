package com.caremarque.patientAuth.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.patientAuth.model.PatientAuthentication;
import com.caremarque.patientAuth.utils.DBConnection;

public class PatientAuthServiceImpl implements IPatientAuthService{

	public static final Logger log = Logger.getLogger(PatientAuthServiceImpl.class.getName());
	
	private static Connection con = null;
	
	private static Statement statement = null;
	
	ResultSet resultSet = null;
	
	
	public List<PatientAuthentication> checkPatientDetails(){
		
		List<PatientAuthentication> patientList = new ArrayList<PatientAuthentication>();
		
		try {
			
			con = DBConnection.getDBConnection();
			
			statement = con.createStatement();
			
			String query = "SELECT email FROM patient";
			
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				
				PatientAuthentication patientAuth = new PatientAuthentication();
				
				patientAuth.setUserName(resultSet.getString("email"));
				System.out.println("authemail : " + resultSet.getString("email"));

				patientList.add(patientAuth);
				System.out.println(patientList);
			}
			System.out.println("Data retrived from DB");
		
		}catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		
		return patientList;
	}
	
	public List<PatientAuthentication> checkPatientDetailsForAppointment(){
		
		List<PatientAuthentication> patientList = new ArrayList<PatientAuthentication>();
		
		try {
			
			con = DBConnection.getDBConnection();
			
			statement = con.createStatement();
			
			String query = "SELECT patientId, firstName, phone FROM patient";
			
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				
				PatientAuthentication patientAuth = new PatientAuthentication();
				
				patientAuth.setPatientId(resultSet.getString("patientId"));
				patientAuth.setFirstName(resultSet.getString("firstName"));
				patientAuth.setPhone(resultSet.getString("phone"));

				patientList.add(patientAuth);
				System.out.println(patientList);
			}
			System.out.println("Data retrived from DB");
		
		}catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		
		return patientList;
	}
}
