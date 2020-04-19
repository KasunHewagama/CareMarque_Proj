package com.caremarque.appadminathentic.service;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import com.caremarque.appadminathentic.model.AdminAuthentication;
import com.caremarque.appadminathentic.utils.DBConnection;



public class AdminAuthServiceImpl implements IAdminAuthService {

	public static final Logger log = Logger.getLogger(AdminAuthServiceImpl.class.getName());
	
	private static Connection con = null;
	
	private static Statement statement = null;
	
	ResultSet resultSet = null;
	
	@Override
	public List<AdminAuthentication> checkAdmindetails() {
		
		List<AdminAuthentication> admiList = new ArrayList<AdminAuthentication>();
		try {

			
			con = DBConnection.getDBConnection();
			
			statement = con.createStatement();
			
			String query = "SELECT email FROM admin";
			
			resultSet = statement.executeQuery(query);
			
			while(resultSet.next()) {
				
				AdminAuthentication adminAuthentication= new AdminAuthentication();
				
				
				adminAuthentication.setUsername(resultSet.getString("username"));



				admiList.add(adminAuthentication);
				System.out.println(admiList);
			}
			System.out.println("Data retrived from DB");
		
		
		} catch (Exception e) {
			// TODO: handle exception
		}
		// TODO Auto-generated method stub
		return admiList;
	}

}

