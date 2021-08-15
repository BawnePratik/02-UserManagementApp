package com.ashokit.resources;

import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.ashokit.entities.UserRegistration;
import com.ashokit.service.UserMasterService;

@RestController
public class UserRegistrationRestController {

	@Autowired
	private UserMasterService userService;
	
	@GetMapping("/getCountries")
	public Map<Integer, String> findCountries(){
		return userService.findCountries();
	}
	
	@GetMapping("/getStates/{countryId}")
	public Map<Integer, String> findStates(@PathVariable("countryId") Integer countryId){
		return userService.findStates(countryId);
	}
	
	@GetMapping("/getCities/{stateId}")
	public Map<Integer, String> findCities(@PathVariable("stateId") Integer stateId){
		return userService.findCities(stateId);
	}
	
	@PostMapping( value = "/registration", consumes = {MediaType.APPLICATION_JSON_VALUE})
	public ResponseEntity<String> userRegistration(@RequestBody UserRegistration user){
		if(userService.isEmailUnique(user.getEmail())) {
			userService.saveUserRegistration(user);
			return new ResponseEntity<String>("Registration is Successfully...!!", HttpStatus.CREATED);
		}else {
			return new ResponseEntity<String>("Registration is failed", HttpStatus.BAD_REQUEST);
		}
	}
	
	@GetMapping(value = "/emailCheck/{email}")
	public String isEmailUnique(@PathVariable String email) {
		if(userService.isEmailUnique(email)) {
			return "Email is Unique";
		}
		else {
			return "Email is Duplicate";
		}
	}
	
}
