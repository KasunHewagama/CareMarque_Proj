package com.caremarque.paymentAuth.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.caremarque.paymentAuth.model.PaymentAuthentication;
import com.caremarque.paymentAuth.utils.Constants;
import com.caremarque.paymentAuth.utils.DBConnection;

public class PaymentAuthService {

	public static final Logger log = Logger.getLogger(PaymentAuthService.class.getName());
	
	public static Connection connecton;
	
	public static Statement statement;
	
	private static PreparedStatement preparedstatement;

	
	public List<PaymentAuthentication> creditCardAuthFromDB() {
	
		List<PaymentAuthentication> authList = new ArrayList<PaymentAuthentication>();
		
		try {
			
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM PAYMENT_CREDIT_CARD";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				PaymentAuthentication pAuth = new PaymentAuthentication();
				pAuth.setAuthId(resultset.getInt(Constants.COLUMN_INDEX_ONE));
				pAuth.setCardNo(resultset.getString(Constants.COLUMN_INDEX_TWO));
				pAuth.setExpDate(resultset.getString(Constants.COLUMN_INDEX_THREE));
				System.out.println("pAuth: " + resultset.getString(Constants.COLUMN_INDEX_THREE));
				pAuth.setPassCode(resultset.getString(Constants.COLUMN_INDEX_FOUR));
				authList.add(pAuth);
			}
			
			System.out.println("Data retrived From DB");
			
		} catch (Exception e) {
			// TODO: handle exception
			log.log(Level.SEVERE, e.getMessage());
		}
		
		return authList;
	}
	
	public ArrayList<PaymentAuthentication> debitCardAuthFromDB() {
		
		ArrayList<PaymentAuthentication> authList = new ArrayList<PaymentAuthentication>();
		
		try {
			
			connecton = DBConnection.getDBConnection();
			
			String query = "SELECT * FROM DEBIT_CREDIT_CARD";
			
			preparedstatement = connecton.prepareStatement(query);
			ResultSet resultset = preparedstatement.executeQuery();
			
			while(resultset.next()) {
				PaymentAuthentication pAuth = new PaymentAuthentication();
				pAuth.setAuthId(resultset.getInt(Constants.COLUMN_INDEX_ONE));
				pAuth.setCardNo(resultset.getString(Constants.COLUMN_INDEX_TWO));
				pAuth.setExpDate(resultset.getString(Constants.COLUMN_INDEX_THREE));
				pAuth.setPassCode(resultset.getString(Constants.COLUMN_INDEX_FOUR));
				authList.add(pAuth);
			}
			
			System.out.println("Data retrived From DB");
			
		} catch (Exception e) {
			// TODO: handle exception
			log.log(Level.SEVERE, e.getMessage());
		}
		
		return authList;
	}

}
