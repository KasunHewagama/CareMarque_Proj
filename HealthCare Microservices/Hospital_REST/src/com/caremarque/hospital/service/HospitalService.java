package com.caremarque.hospital.service;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.hospital.model.Hospital;
import com.caremarque.hospital.utils.DBConnection;

public class HospitalService implements IHospitalService{
	
	public static final Logger Log = Logger.getLogger(IHospitalService.class.getName());

	@Override
	public String createHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		
		String strobj = null;
		Connection con=null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String insertQuery = "INSERT INTO appointment(" + "hospitalId," + "hospitalName," + "phone," + "regNo," + "address," + "Open_Hours," + "Close_Hours) "
			+"VALUES(?,?,?,?,?,?,?)";
			
			preparedStatement = con.prepareStatement(insertQuery);
			
			preparedStatement.setString(1, hospital.getHospitalId());
			preparedStatement.setString(2, hospital.getHospitalName());
			preparedStatement.setString(3, hospital.getPhone());
			preparedStatement.setString(4, hospital.getRegNo());
			preparedStatement.setString(5, hospital.getAddress());
			preparedStatement.setString(6, hospital.getOpen_Hours());
			preparedStatement.setString(7, hospital.getClose_Hours());
			
			preparedStatement.executeUpdate();
			
			strobj = "Hospital  profile created Successfully";
			
		}catch (Exception e) {
			// TODO: handle exception
			
			strobj = "Hospital Profile Not created";
			System.err.println(e.getMessage());
			Log.log(Level.SEVERE, e.getMessage());
		}finally {
			try {
				if(preparedStatement != null) {
					preparedStatement.close();
				}
				if(con !=null) {
					con.close();
				}
			}catch (Exception e) {
			// TODO: handle exception
			Log.log(Level.SEVERE,e.getMessage());
			}
		}
		
		return strobj;
	}

	@Override
	public Hospital getHospital(String hospitalId) {
		// TODO Auto-generated method stub
		return null;
	}

	

	@Override
	public void cancelHospital(String hospitalId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public String getHospitals() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateHospital(Hospital hospital) {
		// TODO Auto-generated method stub
		return null;
	}

}
