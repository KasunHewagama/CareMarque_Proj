package com.caremarque.appadminathentic.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.appadminathentic.model.Admin;
import com.caremarque.appadminathentic.utils.CommonUtils;
import com.caremarque.appadminathentic.utils.DBConnection;


public class AdminServiceImpl implements IAdminService{

	public static final Logger log = Logger.getLogger(IAdminService.class.getName());
	
	public static Connection connection;
	public static Statement Statement;
	
	@Override
	public String createAdmin(Admin admin) {
		String output = null;
		PreparedStatement preparedStatement=null;
		
		String adminId = CommonUtils.generateadminId(getAdminIDs());
		
		try {
			connection = DBConnection.getDBConnection();
			
			String query = "INSERT INTO admin(adminId,username,email,password)" + "VALUES(?, ?, ?, ?)";
			
			preparedStatement = connection.prepareStatement(query);
			
			admin.setAdminId(adminId);
			preparedStatement.setString(1, admin.getAdminId());
			preparedStatement.setString(2, admin.getUsername());
			preparedStatement.setString(3, admin.getEmail());
			preparedStatement.setString(4, admin.getPassword());
			
			preparedStatement.executeUpdate();
			output = "Admin account created";
			
		}catch (Exception e) {
			
			output = "ADmin account not created";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection!=null) {
					connection.close();
				}
			} catch (Exception e) {
				// TODO: handle exception
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	@Override
	public ArrayList<String> getAdminIDs() {
		
		
		PreparedStatement preparedStatement = null;
		ResultSet resutSet = null;
		
		ArrayList<String> arrayList = new ArrayList<String>();
		try {
			connection = DBConnection.getString();
			
			String query = "SELECT admin.adminId FROM hospital";
			
			preparedStatement = connection.prepareStatement(query);
			resutSet = preparedStatement.executeQuery();
			
			while(resutSet.next()) {
				arrayList.add(resutSet.getString(1));
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			log.log(Level.SEVERE, e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(connection != null) {
					connection.close();
				}
			} catch (SQLException e) {
				// TODO: handle exception
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		
	
		// TODO Auto-generated method stub
		return arrayList;
	}

	

}
