package com.ashokit.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.ashokit.entities.CityMaster;
import com.ashokit.entities.CountryMaster;
import com.ashokit.entities.StateMaster;
import com.ashokit.entities.UserRegistration;
import com.ashokit.repository.CityMasterRepository;
import com.ashokit.repository.CountryMasterRepository;
import com.ashokit.repository.StateMasterRepository;
import com.ashokit.repository.UserRegistrationRepository;
import com.ashokit.util.PasswordGenerator;

@Service
public class UserMasterServiceImpl implements UserMasterService{

	private static final String ALPHA = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJ";
	private static final String NUMERIC = "0123456789";
	
	@Autowired
	private IEmailService emailService;
	
	@Autowired
	private UserRegistrationRepository userRepo;
	
	@Autowired
	private CountryMasterRepository countryRepo;

	@Autowired
	private StateMasterRepository stateRepo;
	
	@Autowired
	private CityMasterRepository cityRepo;
	
	@Override
	public Map<Integer, String> findCountries() {
		List<CountryMaster> countriesList = countryRepo.findAll();
		Map<Integer, String> countries = new HashMap<Integer, String>();
		countriesList.forEach(country -> {
			countries.put(country.getCountryId(), country.getCountryName());
		});
		return countries;
	}

	@Override
	public Map<Integer, String> findStates(Integer countryId) {
		List<StateMaster> statesList = stateRepo.findByCountryId(countryId);
		Map<Integer, String> states = new HashMap<Integer, String>();
		statesList.forEach(state -> {
			states.put(state.getStateId(), state.getStateName());
		});
		return states;
	}

	@Override
	public Map<Integer, String> findCities(Integer stateId) {
		List<CityMaster> citiesList = cityRepo.findByStateId(stateId);
		Map<Integer, String> cities = new HashMap<Integer, String>();
		citiesList.forEach(city -> {
			cities.put(city.getCityId(), city.getCityName());
		});
		return cities;
	}

	@Override
	public boolean isEmailUnique(String email) {
		UserRegistration userDetails = userRepo.findByEmail(email);
		if(userDetails==null) {
			return true;
		}else {
			return false;
		}
	//	return userDetails.getUserId()==null;
	}

	@Override
	public boolean saveUserRegistration(UserRegistration userRegistration) {
		userRegistration.setPassword(PasswordGenerator.generatePassword(8, ALPHA + NUMERIC));
		userRegistration.setAcc_Status("LOCKED");
		UserRegistration saveUser = userRepo.save(userRegistration);
		
		String emailBody = getUnlockAccEmailBody(userRegistration);
		String subject = "UNLOCK YOUR ACCOUNT | IES ";
		boolean isSent = emailService.sendAccountUnlockEmail(subject, emailBody, userRegistration.getEmail());
		
		return saveUser.getUserId()!=null && isSent;
	}

	//Test-1 -> Invalid emailid & password -> CREDENTIALS ARE INVALID
	//Test-2 -> valid credentials & acc is Locked ->  ACCOUNT IS LOCKED
	//Test-3 -> valid credentials & acc is unlocked -> LOGIN IS SUCCESS
	@Override
	public String loginCheck(String email, String password) {
		UserRegistration userDetails = userRepo.findByEmailAndPassword(email, password);
		if(userDetails != null) {
			if(userDetails.getAcc_Status().equals("LOCKED")) {
				return "ACCOUNT IS LOCKED";
			}else {
				return "LOGIN SUCCESS";
			}
		}
		return "CREDENTIALS ARE INVALID";
	}

	//Test case 1-> User has given invalid temp-pwd ==> false
	//Test case 2-> User has given valid temp-pwd ==> true
	@Override
	public boolean isTempPwdValid(String email, String tempPwd) {
		UserRegistration userDetails = userRepo.findByEmailAndPassword(email, tempPwd);
		return userDetails!=null;
	}

	@Override
	public boolean unlockAccount(String email, String newPwd) {
		UserRegistration userDetails = userRepo.findByEmail(email);
		userDetails.setPassword(newPwd);
		userDetails.setAcc_Status("UNLOCKED");
		try {
			userRepo.save(userDetails);
			return true;
		}catch(Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	//Test case : 1 -> user entered valid email -> return existing pwd
	//Test case : 2 -> user entered invalid email -> NPE
	@Override
	public String forgotPassword(String email) {
		UserRegistration userDetails = userRepo.findByEmail(email);
		if(userDetails!=null) {
			return userDetails.getPassword();
		}
		return null;
	}
	
	private String getUnlockAccEmailBody(UserRegistration ur) {
		StringBuffer sb = new StringBuffer("");
		String body = null;
		try {
			File f = new File("UNLOCK_ACC-_EMAIL_BODY.txt");
			FileReader fr = new FileReader(f);
			BufferedReader br = new BufferedReader(fr);
			String line = br.readLine();
			while(line!=null) {
				sb.append(line);
				line = br.readLine();
			}
			br.close();
			body = sb.toString();
			body = body.replace("{FNAME}", ur.getfName());
			body = body.replace("{LNAME}", ur.getlName());	
			body = body.replace("{TEMP_PWD}", ur.getPassword());
			body = body.replace("{EMAIL}", ur.getEmail());
		}	
			catch (Exception e) {
				e.printStackTrace();
			}
			return body;
	}

}
