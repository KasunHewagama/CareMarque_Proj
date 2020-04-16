package com.caremarque.userAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.caremarque.userAuth.utils.DBConnection;

public class UserAuthService {

	private static Connection con = null;
	private static PreparedStatement preparedStmt = null;
	
	public boolean checkPatientDeails(String userName, String password) {
		
		try {
			con = DBConnection.getDBConnection();
			
			String query ="SELECT * FROM patient WHERE email = ? and password = ? ";
			
			preparedStmt = con.prepareStatement(query);
			
			preparedStmt.setString(1, userName);
			preparedStmt.setString(2, password);
			
			ResultSet rs = preparedStmt.executeQuery();
			
			if(rs.next()) {
				return true;
			}
		}catch (Exception e) {
			System.err.println(e.getMessage());
		}
		
		return false;
	}
}
