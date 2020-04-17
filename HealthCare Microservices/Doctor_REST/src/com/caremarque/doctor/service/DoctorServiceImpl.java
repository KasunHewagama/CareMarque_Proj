package com.caremarque.doctor.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.doctor.model.Doctor;
import com.caremarque.doctor.util.CommonUtils;
import com.caremarque.doctor.util.DBConnection;

public class DoctorServiceImpl implements IDoctorService {

	//This object is for logging
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
	public String getDoctor(String doctorId) {
		// TODO Auto-generated method stub
		
		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		
try {
			
			con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM doctor doctorId = '"+ doctorId + "'";
			
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			output = "<table border=\"1\">"						
					+"<tr>"+"<th>firstName</th>"
					+"<th>lastName</th>"
					+"<th>regNo</th>"
					+"<th>gender</th>"
					+"<th>specialization</th>"
					+"<th>phone</th>"
					+"<th>email</th>"
					+"<th>password</th>"
					+"<th>confirmPassword</th></tr>";
			
			while(rs.next()) {
				
				//String doctorId = Integer.toString(rs.getInt("doctorId"));
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String regNo =  rs.getString("regNo");
				String gender = rs.getString("gender");
				String specialization = rs.getString("specialization");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String confirmPassword = rs.getString("confirmPassword");
				
				output += "<tr><td>" + doctorId + "</td>";
				output += "<tr><td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + regNo + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + confirmPassword + "</td>";
			}
			
			output += "</table>";
		} catch(Exception e) {
			
			output = "Error while reading doctor details...!";
			System.err.println(e.getMessage());
		
		}finally {
			
			try {
				
				if (st != null) {
					st.close();
				}
				
				if (con != null) {
					con.close();
				}
				
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return output;
	}
	
	
	@Override
	public String getDoctors() {
		// TODO Auto-generated method stub

		String output = "";
		Statement st = null;
		ResultSet rs = null;
		Connection con = null;
		
		try {
			
			con = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM doctor";
			
			st = con.createStatement();
			rs = st.executeQuery(query);
			
			output = "<table border=\"1\">"						
					+"<tr>"+"<th>doctorId</th>"
					+"<th>firstName</th>"
					+"<th>lastName</th>"
					+"<th>regNo</th>"
					+"<th>gender</th>"
					+"<th>specialization</th>"
					+"<th>phone</th>"
					+"<th>email</th>"
					+"<th>password</th>"
					+"<th>confirmPassword</th></tr>";
			
			while(rs.next()) {
				//String doctorId = Integer.toString(rs.getInt("doctorId"));
				String doctorId = rs.getString("doctorId");
				String firstName = rs.getString("firstName");
				String lastName = rs.getString("lastName");
				String regNo =  rs.getString("regNo");
				String gender = rs.getString("gender");
				String specialization = rs.getString("specialization");
				String phone = rs.getString("phone");
				String email = rs.getString("email");
				String password = rs.getString("password");
				String confirmPassword = rs.getString("confirmPassword");
				
				output += "<tr><td>" + doctorId + "</td>";
				output += "<td>" + firstName + "</td>";
				output += "<td>" + lastName + "</td>";
				output += "<td>" + regNo + "</td>";
				output += "<td>" + gender + "</td>";
				output += "<td>" + specialization + "</td>";
				output += "<td>" + phone + "</td>";
				output += "<td>" + email + "</td>";
				output += "<td>" + password + "</td>";
				output += "<td>" + confirmPassword + "</td></tr>";
			}
			
			output += "</table>";
		} catch(Exception e) {
			
			output = "Error while reading doctor details...!";
			System.err.println(e.getMessage());
		
		}finally {
			
			try {
				
				if (st != null) {
					st.close();
				}
				
				if (con != null) {
					con.close();
				}
				
				if(rs != null) {
					rs.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
		}
		return output;

			}
	
	
	 @Override
		public String updateDoctor(String doctorId,Doctor doctor) {
			// TODO Auto-generated method stub
			//System.out.println("ABC");
			//return null; 
			
			String output = "";
			Connection con = null;
			PreparedStatement preparedStatement = null;
			
			try {
				
				con = DBConnection.getDBConnection();
				String query = "UPDATE doctor SET doctorId=?,firstName=?, lastName=?, regNo=?, gender=?, specialization=?, phone=?, email=?, password=?, confirmPassword=? WHERE doctorId=?";
				
				preparedStatement = con.prepareStatement(query);
				
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
				preparedStatement.setString(11, doctor.getDoctorId());
				preparedStatement.execute();
				
				output = "Updated Successfully....!";
					
				
			}catch(Exception e) {
				
				output = "Error while updating the doctor..!";
				System.out.println(e.getMessage());
			}finally {
				try {
					if(preparedStatement != null) {
						preparedStatement.close();
					}
					
					if(con != null) {
						con.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
			return output;
		}


		@Override
		public String cancelDoctor(String doctorId) {
			/* TODO Auto-generated method stub
			return null; */
			
			String output = "";
			PreparedStatement preparedStatmnt = null;
			Connection con = null;
			
			try {
				
				con = DBConnection.getDBConnection();
				
				String query = "DELETE FROM doctor WHERE doctorId = ?";
				
				preparedStatmnt = con.prepareStatement(query);
				
				//preparedStatmnt.setInt(1, Integer.parseInt(doctorId));
				preparedStatmnt.setString(1, doctorId);
				
				preparedStatmnt.execute();
				
				output = "Deleted successfully..!";
				

			}catch(Exception e) {
				output = "Error while deleting item..!";
				System.out.println(e.getMessage());
			}finally {
				
				try {
					if(preparedStatmnt != null) {
						preparedStatmnt.close();
					}
					
					if(con != null) {
						con.close();
					}
				}catch(Exception e) {
					e.printStackTrace();
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
