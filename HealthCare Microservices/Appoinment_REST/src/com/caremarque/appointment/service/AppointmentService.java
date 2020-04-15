package com.caremarque.appointment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPopupMenu.Separator;

//import org.apache.tomcat.util.security.Escape;

import com.caremarque.appointment.model.Appointment;
import com.caremarque.appointment.utils.DBConnection;
import com.mysql.cj.log.Log;

public class AppointmentService implements IAppointmentService {

	public static final Logger log = Logger.getLogger(IAppointmentService.class.getName());

	@Override
	public String createAppointment(Appointment appointment) {
		// TODO Auto-generated method stub
		// return "Appointment created successfully...!";

		String output = "";
		Connection con = null;
		PreparedStatement preparedStatement = null;

		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO appointment(" + "patientId," + "patientName," + "phone," + "doctorName,"
					+ "specialization," + "hospitalId," + "hospitalName," + "appointmentDate," + "appointmentTime,"
					+ "lastUpdateDate," + "lastUpdateTime," + "appointmentStatus) "
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

			output = "<table border=\"1\"> <tr><th>appointmentId</th> " 
					+ "<th>patientId</th> " 
					+ "<th>patientName</th> " 
					+ "<th>phone</th> "
					+ "<th>doctorName</th> " 
					+ "<th>specialization</th> " 
					+ "<th>hospitalId</th> "
					+ "<th>hospitalName</th> " 
					+ "<th>appointmentDate</th> " 
					+ "<th>appointmentTime</th> "
					+ "<th>lastUpdateDate</th> " 
					+ "<th>lastUpdateTime</th> " 
					+ "<th>appointmentStatus</th></tr>";

			while (rs.next()) {
				String appointmentId = rs.getString("appointmentId");
				String patientId = rs.getString("patientId");
				String patientName = rs.getString("patientName");
				String phone = rs.getString("phone");
				String doctorName = rs.getString("doctorName");
				String specialization = rs.getString("specialization");
				String hospitalId = rs.getString("hospitalId");
				String hospitalName = rs.getString("hospitalName");
				String appointmentDate = rs.getString("appointmentDate");
				String appointmentTime = rs.getString("appointmentTime");
				String lastUpdateDate = rs.getString("lastUpdateDate");
				String lastUpdateTime = rs.getString("lastUpdateTime");
				String appointmentStatus = rs.getString("appointmentStatus");

				output += "<tr><td>" + appointmentId + "</td>";
				output += "<td>" + patientId + "</td>";
				output += "<td>" + patientName + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + doctorName + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + hospitalId + "</td>";
				output += "<td>" + hospitalName + "</td>";
				output += "<td>" + appointmentDate + "</td>";
				output += "<td>" + appointmentTime + "</td>";
				output += "<td>" + lastUpdateDate + "</td>";
				output += "<td>" + lastUpdateTime + "</td>";
				output += "<td>" + appointmentStatus + "</td></tr>";

			}

			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading appointment details...!";
			System.err.println(e.getMessage());

		} finally {

			try {
				if (st != null) {
					st.close();
				}

				if (con != null) {
					con.close();
				}

				if (rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

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
