package com.caremarque.userAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import com.caremarque.userAuth.model.loginAuthentication;
import com.caremarque.userAuth.utils.DBConnection;

public class LoginAuthenticationServiceImpl implements ILoginAuthentication{

	private static final Logger logger = Logger.getLogger(LoginAuthenticationServiceImpl.class.getName());

	private static Connection con;
	
	private static PreparedStatement preparedStmt;
	
	private static Statement statement;
	
	@Override
	public List<loginAuthentication> checkLoginCredentials() {
		
		List<loginAuthentication> loginDetailList = new ArrayList<loginAuthentication>();
		
		try {
			
			con = DBConnection.getDBConnection();
			
			statement = con.createStatement();
			
			
			
			
		}catch (Exception e) {
			// TODO: handle exception
		}
		
		return loginDetailList;
		
		
		
		
	}

}
