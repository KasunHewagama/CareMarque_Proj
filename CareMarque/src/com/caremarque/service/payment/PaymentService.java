package com.caremarque.service.payment;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import com.caremarque.model.Payment;
import com.caremarque.utils.CommonUtils;
import com.caremarque.utils.Constants;
import com.caremarque.utils.DBConnection;
import com.mysql.cj.protocol.Resultset;

public class PaymentService implements IPaymentService {
	
	//this object is for logging 
	public static final Logger log = Logger.getLogger(IPaymentService.class.getName());
	
	public static Connection connecton;
	
	public static Statement statement;
	
	private static PreparedStatement preparedstatement;

	@Override
	public String createPayement(Payment p) {
		
		String output = null;
		
		//Here we call the generatePaymentIDs method to auto generate a paymentID
		//To do that we pass the existing paymentid set as an arraylist
		String paymentId = CommonUtils.generatePaymentIDs(getPaymentIDs());
		
		try {
			
				connecton = DBConnection.getDBConnection();
				
				if(connecton == null)
				{
					return "Error occured while connecting to the database for inserting";
				}
				
				String query = "INSERT INTO PAYMENTS "
						+ "(paymentId, patientId, patientName, appointmentId, doctorId, hospitalId, paymentDate,"
						+ " doctorCharges, hospitalCharges, totalAmount, paymentStatus)"
						+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				
				preparedstatement = connecton.prepareStatement(query);
				
				p.setPaymentId(paymentId);
				preparedstatement.setString(Constants.COLUMN_INDEX_ONE, p.getPaymentId());
				preparedstatement.setString(Constants.COLUMN_INDEX_TWO, p.getPatientId());
				preparedstatement.setString(Constants.COLUMN_INDEX_THREE, p.getPatientName());
				preparedstatement.setString(Constants.COLUMN_INDEX_FOUR, p.getAppointmentId());
				preparedstatement.setString(Constants.COLUMN_INDEX_FIVE, p.getDoctorId());
				preparedstatement.setString(Constants.COLUMN_INDEX_SIX, p.getHospitalId());
				preparedstatement.setDate(Constants.COLUMN_INDEX_SEVEN, p.getPaymentDate());
				preparedstatement.setDouble(Constants.COLUMN_INDEX_EIGHT, p.getDoctorCharges());
				preparedstatement.setDouble(Constants.COLUMN_INDEX_NINE, p.getHospitalCharges());
				preparedstatement.setDouble(Constants.COLUMN_INDEX_TEN, p.getDoctorCharges() +  p.getHospitalCharges());
				preparedstatement.setString(Constants.COLUMN_INDEX_ELEVEN, p.getPaymentStatus());
				preparedstatement.execute();
				
				output = "Data inserted successfully!";

		} catch (Exception e) {
			
			log.log(Level.SEVERE, e.getMessage());
			output = "Error while inserting data to payments table";
		
		} finally {
			
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return output;
	}

	@Override
	public Payment getPayment(String paymentId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Payment> getPayments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Payment updatePayment(String paymentid, Payment p) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void cancelPayment(String paymentId) {
		// TODO Auto-generated method stub
		
	}

	//This method get all the existing paymentids and put them into a arraylist 
	@Override
	public ArrayList<String> getPaymentIDs() {
		ArrayList<String> arrayList = new ArrayList<String>();
		
		
		try {
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT PAYMENTS.PAYMENTID FROM PAYMENTS";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				
				arrayList.add(resultset.getString(Constants.COLUMN_INDEX_ONE));
				
			}
			
		} catch (Exception e) {
			log.log(Level.SEVERE, e.getMessage());
		} finally {
			try {
				if(preparedstatement != null) {
					preparedstatement.close();
				}
				if(connecton != null) {
					connecton.close();
				}
			} catch (SQLException e) {
				log.log(Level.SEVERE, e.getMessage());
			}
		}
		return arrayList;
	}
	
	

}
