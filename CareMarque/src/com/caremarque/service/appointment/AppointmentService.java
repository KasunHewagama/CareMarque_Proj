package com.caremarque.service.appointment;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import com.caremarque.model.Appointment;
import com.caremarque.utils.DBConnection;

public class AppointmentService implements IAppointmentService{

	@Override
	public String createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		//return "Appointment created successfully...!";
		
		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "INSERT iNTO appointment(appointmentId,patientId,PatientName,phone,doctorName,hospitalName,hospitalId,appointmentDate,lastUpdateDate,appointmentTime,lastUpdateTime,specialization,appointmentStatus) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			
			preparedStatement = con.prepareStatement(query);
			
			preparedStatement.setString(1, appointment.getPatientId());
			preparedStatement.setString(2, appointment.getPatientName());
			preparedStatement.setString(3, appointment.getPhone());
			preparedStatement.setString(4, appointment.getDoctorName());
			preparedStatement.setString(5, appointment.getHospitalName());
			preparedStatement.setString(6, appointment.getHospitalId());
			preparedStatement.setString(7, appointment.getAppointmentDate());
			preparedStatement.setString(8, appointment.getLastUpdateDate());
			preparedStatement.setString(9, appointment.getAppointmentTime());
			preparedStatement.setString(10, appointment.getLastUpdateTime());
			preparedStatement.setString(11, appointment.getSpecialization());
			preparedStatement.setString(12, appointment.getAppointmentStatus());
			
			preparedStatement.executeUpdate();
			
			output = "Inserted Successfully...!";
			
		} catch (Exception e) {
			
			output = "Error when Inserting the Appointment...!";
			System.err.println(e.getMessage());
			
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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String updateAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelAppointment(String appointmnetId) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
