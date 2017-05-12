package com.cr.ac.ucr.lenguajes.j2fshop.business;

public interface SecurityService {
	String findLoggedInUsername();

	void autologin(String username, String password);
}
