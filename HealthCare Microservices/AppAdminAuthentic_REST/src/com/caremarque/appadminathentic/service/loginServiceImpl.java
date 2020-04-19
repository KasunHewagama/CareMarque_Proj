package com.caremarque.appadminathentic.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.logging.Logger;

import com.caremarque.appadminathentic.utils.DBConnection;



public class loginServiceImpl{
	private static final Logger logger = Logger.getLogger(loginServiceImpl.class.getName());

	private static Connection con;
	
	private static PreparedStatement preparedStmt;
	
	private static Statement statement;
	
	
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
							
			}
			
		}catch (Exception e) {
			
		}
		return false;
		
		
	}
	
	public String issueToken(String userName) {
		return userName;
		
	}

}
