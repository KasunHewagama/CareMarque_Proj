package com.caremarque.paymentAuth.model;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PaymentAuthentication {

	private int authId;
	private String cardNo;
	private String expDate;
	private String passCode;
	
	public PaymentAuthentication() {}
	
	public int getAuthId() {
		return authId;
	}
	
	public void setAuthId(int authId) {
		this.authId = authId;
	}

	public String getCardNo() {
		return cardNo;
	}
	public void setCardNo(String cardNo) {
		this.cardNo = cardNo;
	}
	public String getExpDate() {
		return expDate;
	}
	public void setExpDate(String expDate) {
		this.expDate = expDate;
	}
	public String getPassCode() {
		return passCode;
	}
	public void setPassCode(String passCode) {
		this.passCode = passCode;
	}
	
	
}
