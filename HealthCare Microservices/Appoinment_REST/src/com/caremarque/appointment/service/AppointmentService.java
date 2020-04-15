package com.caremarque.appointment.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPopupMenu.Separator;

//import org.apache.tomcat.util.security.Escape;

import com.caremarque.appointment.model.Appointment;
import com.caremarque.appointment.utils.CommonUtils;
import com.caremarque.appointment.utils.DBConnection;
import com.mysql.cj.log.Log;

public class AppointmentService implements IAppointmentService {

	//this object is for logging
	public static final Logger log = Logger.getLogger(IAppointmentService.class.getName());

	@Override
	public String createAppointment(Appointment appointment) {
		// return "Appointment created successfully...!";

		String output = null;
		Connection con = null;
		PreparedStatement preparedStatement = null;
		
		//Here we call the generateAppointmentIDs method to auto generate a appointmentID
		//To do that we pass the existing appointmentid set as an arraylist
		String appointmentId = CommonUtils.generateAppointmentIDs(getAppointmentIDs());
		System.out.println("AppointmentID: " +  appointmentId);


		try {
			con = DBConnection.getDBConnection();

			String query = "INSERT INTO appointment(" 
					+ "appointmentId,patientId,patientName,phone,doctorName,"
					+ "specialization,hospitalId,hospitalName,appointmentDate,appointmentTime,"
					+ "lastUpdateDate,lastUpdateTime,appointmentStatus)"
					+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

			preparedStatement = con.prepareStatement(query);

			appointment.setAppointmentId(appointmentId);
			preparedStatement.setString(1, appointment.getAppointmentId());
			preparedStatement.setString(2, appointment.getPatientId());
			preparedStatement.setString(3, appointment.getPatientName());
			preparedStatement.setString(4, appointment.getPhone());
			preparedStatement.setString(5, appointment.getDoctorName());
			preparedStatement.setString(6, appointment.getSpecialization());
			preparedStatement.setString(7, appointment.getHospitalId());
			preparedStatement.setString(8, appointment.getHospitalName());
			preparedStatement.setString(9, appointment.getAppointmentDate());
			preparedStatement.setString(10, appointment.getAppointmentTime());
			preparedStatement.setString(11, LocalDate.now().toString());
			preparedStatement.setString(12, LocalTime.now().toString());
			preparedStatement.setString(13, appointment.getAppointmentStatus());

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
				log.log(Level.SEVERE, e.getMessage());
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

		String output = null;
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		
		ArrayList<Appointment> arrayList = new ArrayList<Appointment>();

		try {
			con = DBConnection.getDBConnection();

			String query = "SELECT * FROM appointment";

			st = con.createStatement();
			rs = st.executeQuery(query);
			
			DateFormat inputFormat = new SimpleDateFormat("dd/MM/yyyy");
			

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
				Appointment appointment = new Appointment();
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
				arrayList.add(appointment);

				output += "<tr><td>" + patientId + "</td>";
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
				
				
				System.out.println("Data Retrieved from database...!");
			}

			output += "</table>";

		} catch (Exception e) {

			output = "Error while reading appointment details...!";
			System.err.println(e.getMessage());
			log.log(Level.SEVERE, e.getMessage());

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
				log.log(Level.SEVERE, e.getMessage());
			}

		}
		return output;
	}

	@Override
	public String updateAppointment(String appointmentid, Appointment appointment) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String cancelAppointment(String appointmnetId) {
		
		String output = "";
		PreparedStatement pStatement = null;
		Connection con = null;
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "DELETE FROM appointment WHERE appointmentId = ?";
			
			pStatement = con.prepareStatement(query);
			
			pStatement.setString(1, appointmnetId);
			
			pStatement.execute();
			
			output = "Deleted Successfully...!";
			
		} catch (Exception e) {

			output = "Error while deleting the appointment";
			System.err.println(e.getMessage());
		
		} finally {

			try {
				if (pStatement != null) {
					pStatement.close();
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

	//This method get all the existing appointmentids and put them into a arraylist
	@Override
	public ArrayList<String> getAppointmentIDs() {
		
		PreparedStatement preparedStatement = null;
		ResultSet rs = null;
		Connection con = null;
		
		ArrayList<String> arrayList = new ArrayList<String>();
		
		try {
			con = DBConnection.getDBConnection();
			
			String query = "SELECT appointment.appointmentId FROM appointment";
			
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
