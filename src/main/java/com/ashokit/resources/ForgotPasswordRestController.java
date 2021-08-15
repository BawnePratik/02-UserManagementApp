package com.ashokit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.ashokit.service.UserMasterService;

@RestController
public class ForgotPasswordRestController {

	@Autowired
	private UserMasterService userService;
	
	@GetMapping("/forgotPassword")
	public String getUserPassword(@RequestParam String emailId) {
		String forgotPassword = userService.forgotPassword(emailId);
		if(forgotPassword != null) {
			return userService.forgotPassword(emailId);
		}else {
			return "User Doesnot Exist";
		}
	}	
}
