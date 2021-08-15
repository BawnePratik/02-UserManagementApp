package com.ashokit.service;

public interface IEmailService {

	public boolean sendAccountUnlockEmail(String subject, String body, String to);
	
}
