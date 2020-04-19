package com.caremarque.appadminathentic.service;

import java.util.List;


public interface IloginAuthentication<E> {
	public List<E> checkLoginCredentials();
}
