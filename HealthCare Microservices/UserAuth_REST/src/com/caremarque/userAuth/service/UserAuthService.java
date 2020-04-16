package com.caremarque.userAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.userAuth.model.PatientAuthentication;
import com.caremarque.userAuth.utils.DBConnection;

public class UserAuthService {

	public static final Logger log = Logger.getLogger(UserAuthService.class.getName());
	
	private static Connection con = null;
	
	private static PreparedStatement preparedStmt = null;
	
	ResultSet resultSet = null;
	
	
	public List<PatientAuthentication> checkPatientDetails(){
		
		List<PatientAuthentication> patientList = new ArrayList<PatientAuthentication>();
		
		try {
			
			con = DBConnection.getDBConnection();
			
			String query = "SELECT patientId, email, password FROM patient";
			
			preparedStmt = con.prepareStatement(query);
			
			while(resultSet.next()) {
				
				PatientAuthentication patientAuth = new PatientAuthentication();
				
				patientAuth.setPatientAuthId(resultSet.getInt("patientId"));
				System.out.println("authId : " + resultSet.getInt("patientId"));
				
				patientAuth.setUserName(resultSet.getString("email"));
				System.out.println("authemail : " + resultSet.getInt("email"));

				patientAuth.setPassword(resultSet.getString("password"));
				System.out.println("authpassword : " + resultSet.getInt("password"));

				patientList.add(patientAuth);
			}
			System.out.println("Data retrived from DB");
		
		}catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
		
		return patientList;
	}
}
