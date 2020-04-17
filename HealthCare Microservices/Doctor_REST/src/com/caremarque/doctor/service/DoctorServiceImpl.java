package com.caremarque.doctor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.util.CommonUtils;
import com.caremarque.doctor.util.DBConnection;

public class DoctorServiceImpl implements IDoctorService {

public static final Logger log = Logger.getLogger(IDoctorService.class.getName());
	
	@Override
	public String createDoctor(Doctor doctor) {
		// TODO Auto-generated method stub
		//return "Appointment created successfully...!";
		
		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		String doctorId = CommonUtils.generateDoctorIDs(getDoctorIDs());
		System.out.println("DoctorID: " +  doctorId);
		
		try {
			
			con = DBConnection.getDBConnection();
			
			String query = "INSERT INTO doctor("
						+"doctorId,"
						+"firstName,"
						+"lastName,"
						+"regNo,"
						+"gender,"
						+"specialization,"
						+"phone,"
						+"email,"
						+"password,"
						+"confirmPassword)"
						+"VALUES(?,?,?,?,?,?,?,?,?,?)";
			
			preparedStatement = con.prepareStatement(query);
			
			doctor.setDoctorId(doctorId);
			preparedStatement.setString(1, doctor.getDoctorId());
			preparedStatement.setString(2, doctor.getFirstName());
			preparedStatement.setString(3, doctor.getLastName());
			preparedStatement.setString(4, doctor.getRegNo());
			preparedStatement.setString(5, doctor.getGender());
			preparedStatement.setString(6, doctor.getSpecialization());
			preparedStatement.setString(7, doctor.getPhone());
			preparedStatement.setString(8, doctor.getEmail());
			preparedStatement.setString(9, doctor.getPassword());
			preparedStatement.setString(10, doctor.getConfirmPassword());
			
			
			preparedStatement.executeUpdate();
			
			output = "Inserted Successfully...!";
		
		}catch (Exception e) {
			
			output = "Error when Inserting the Doctor...!";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());
			
			
		} finally {
			
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}

				if (con != null) {
					con.close();
				}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		}
	}
		return output;
		

}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	@Override
	public ArrayList<String> getDoctorIDs() {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection con = null;
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT doctor.doctorId FROM doctor";
			
			preparedStatement = con.prepareStatement(query);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				
				arrayList.add(rs.getString(1));
				
			}
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
				if (con != null) {
					con.close();
				}
			} catch (SQLException e) {
			log.log(Level.SEVERE, e.getMessage());
			}

		}
		System.out.println(arrayList.size());
		return arrayList;
	}
}
