package com.ashokit.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ashokit.entities.UserRegistration;

public interface UserRegistrationRepository extends JpaRepository<UserRegistration, Integer>{

	UserRegistration findByEmail(String emailId);
	
	UserRegistration findByEmailAndPassword(String email, String password);
	
}
