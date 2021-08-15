package com.ashokit.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashokit.model.LoginForm;
import com.ashokit.service.UserMasterService;

@RestController
public class LoginRestController {

	@Autowired
	private UserMasterService userService;
	
	@PostMapping("/checkLogin")
	public String loginCheck(@RequestBody LoginForm loginForm) {
		return userService.loginCheck(loginForm.getEmail(), loginForm.getPassword());
	}	
}
