package com.ashokit.service;

import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class IEmailServiceImpl implements IEmailService{

	@Autowired
    JavaMailSender mailSender;
	
	@Override
	public boolean sendAccountUnlockEmail(String subject, String body, String to) {
		
		try {
			MimeMessage mailMessage = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(mailMessage);
			
			helper.setTo(to);
			helper.setSubject(subject);
			helper.setText(body, true);  //Try without true also
			
			mailSender.send(mailMessage);
			System.out.println("**********Email Sent Successfully to Registered User**********" + to);
			return true;
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
