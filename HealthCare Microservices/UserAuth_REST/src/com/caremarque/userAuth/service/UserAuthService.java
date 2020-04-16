package com.caremarque.userAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.caremarque.userAuth.model.userAuth;
import com.caremarque.userAuth.utils.DBConnection;

public class UserAuthService {

	private static Connection con = null;
	private static PreparedStatement preparedStmt = null;
	ResultSet resultSet = null;
	
	
//	public List<userAuth> checkPatientDetails(){
//		
//		List<userAuth> patientList = new ArrayList<userAuth>();
//		
//		try {
//			
//			con = DBConnection.getDBConnection();
//			
//			String query = "SELECT * FROM patient";
//			
//			preparedStmt = con.prepareStatement(query);
//			
//			while(resultSet.next()) {
//				
//				userAuth patientAuth = new userAuth();
//				
//				patientAuth.setUserAuthId(resultSet.getInt(1));
//				patientAuth.set
//			}
//		}
//		
//		return patientList;
//	}
}
