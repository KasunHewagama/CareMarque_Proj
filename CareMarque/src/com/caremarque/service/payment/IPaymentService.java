package com.caremarque.service.payment;

import java.util.ArrayList;

import com.caremarque.model.Payment;

public interface IPaymentService {
	
//TODO: Add all methods which should be implemented in service
	public String createPayement(Payment p);
	
	public Payment getPayment(String paymentId);
	
	public ArrayList<Payment> getPayments();
	
	public Payment updatePayment(String paymentid, Payment p);
	
	public void cancelPayment(String paymentId);

}
