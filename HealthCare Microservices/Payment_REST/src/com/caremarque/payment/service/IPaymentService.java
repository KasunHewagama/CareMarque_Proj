package com.caremarque.payment.service;

import java.util.ArrayList;

import com.caremarque.payment.model.Payment;

public interface IPaymentService {
	
//TODO: Add all methods which should be implemented in service
	public String createPayement(Payment p);
	
	public String getPaymentById(String paymentId);
	
	public String getPayments();
	
	public String updatePayment(String paymentid, String telPhone, String email);
	
	public String cancelPayment(String paymentId);
	
	public ArrayList<String> getPaymentIDs();

}
