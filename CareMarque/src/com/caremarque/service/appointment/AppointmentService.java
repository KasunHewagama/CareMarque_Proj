package com.caremarque.service.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.model.Appointment;
import com.caremarque.utils.DBConnection;
import com.mysql.cj.log.Log;

public class AppointmentService implements IAppointmentService{

	public static final Logger log = Logger.getLogger(IAppointmentService.class.getName());
	
	@Override
	public String createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		//return "Appointment created successfully...!";
		
		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "INSERT INTO appointment("
					+ "patientId,"
					+ "patientName,"
					+ "phone,"
					+ "doctorName,"
					+ "specialization,"
					+ "hospitalId,"
					+ "hospitalName,"
					+ "appointmentDate,"
					+ "appointmentTime,"
					+ "lastUpdateDate,"
					+ "lastUpdateTime,"
					+ "appointmentStatus) "
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, appointment.getPatientId());
			preparedStatement.setString(2, appointment.getPatientName());
			preparedStatement.setString(3, appointment.getPhone());
			preparedStatement.setString(4, appointment.getDoctorName());
			preparedStatement.setString(5, appointment.getSpecialization());
			preparedStatement.setString(6, appointment.getHospitalId());
			preparedStatement.setString(7, appointment.getHospitalName());
			preparedStatement.setString(8, appointment.getAppointmentDate());
			preparedStatement.setString(9, appointment.getAppointmentTime());
			preparedStatement.setString(10, appointment.getLastUpdateDate());
			preparedStatement.setString(11, appointment.getLastUpdateTime());
			preparedStatement.setString(12, appointment.getAppointmentStatus());
			
			preparedStatement.executeUpdate();
			
			output = "Inserted Successfully...!";
			
		} catch (Exception e) {
			
			output = "Error when Inserting the Appointment...!";
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
				e.printStackTrace();
			}
		}

		return output;

	}

	@Override
	public Appointment getAppointment(String appointmentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getAppointments() {
		
		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM appointment";
			
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			output = "<table border=\"1\"> "
					+ "<tr><th>appointmentId</th> "
					+ "<th>patientId</th> "
					+ "<th>patientName</th> "
					+ "<th>patientName</th> "
					+ "<th>patientName</th> "
					+ "<th>patientName</th> "
					+ "<th>patientName</th> "
					+ "<th>patientName</th> "
					+ "<th>NIC</th></tr>";
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return output;


	}

	@Override
	public String updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		System.out.println("ABC");
		return null;
	}

	@Override
	public String cancelAppointment(String appointmnetId) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
