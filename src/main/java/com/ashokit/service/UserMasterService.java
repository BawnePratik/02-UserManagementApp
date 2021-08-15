package com.ashokit.service;

import java.util.Map;

import com.ashokit.entities.UserRegistration;

public interface UserMasterService {

	//Registration Page Operations
	public Map<Integer, String> findCountries();
	
	public Map<Integer, String> findStates(Integer countryId);
	
	public Map<Integer, String> findCities(Integer stateId);
	
	public boolean isEmailUnique(String email);
	
	public boolean saveUserRegistration(UserRegistration userRegistration);
	
	//Login Page Operations
	public String loginCheck(String email, String password);
	
	//Unlock Account Operations
	public boolean isTempPwdValid(String email, String tempPwd);
	
	public boolean unlockAccount(String email, String newPwd);
	
	//Forgot password operations
	public String forgotPassword(String email);
	
	
	
	
}
