package com.caremarque.appadminathentic.service;


import java.util.List;

import com.caremarque.appadminathentic.model.adminlogin;


public interface IloginService {

	public List<adminlogin> checkLoginCredentials();
	
}
