package com.caremarque.service.payment;

import com.caremarque.model.Payment;

public interface IPaymentStatusHandling {

	public String addPaymentToStatus(Payment p);
	
	public String updatePaymentStatus(Payment p);
	
}
