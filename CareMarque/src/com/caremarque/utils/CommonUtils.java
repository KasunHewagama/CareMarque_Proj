package com.caremarque.utils;

import java.util.ArrayList;

public class CommonUtils {
	//TODO: Here we can implement some common functions used in though out the project 
	
	//*************************Payment ID Generator*************************
	//It takes the all the values from the payment table and get the count then increase the count by 1
	//and create next id with the given prefix
	public static String generatePaymentIDs(ArrayList<String> arrayList) {
		
		String id;
		int next = arrayList.size();
		next++;
		id = Constants.PAYMENT_ID_PREFIX + next;
		if(arrayList.contains(id)) {
			next++;
			id = Constants.PAYMENT_ID_PREFIX + next;
		}
		return id;
	}
}
