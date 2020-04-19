package com.caremarque.userAuth.service;

import java.util.List;
import com.caremarque.userAuth.model.loginAuthentication;


public interface ILoginAuthentication {
	
	public List<loginAuthentication> checkLoginCredentials();
}
