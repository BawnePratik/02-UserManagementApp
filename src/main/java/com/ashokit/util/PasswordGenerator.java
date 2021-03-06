package com.ashokit.util;

import java.security.SecureRandom;

public class PasswordGenerator {

	private static SecureRandom random = new SecureRandom();
	
	/**
    * Method will generate random string based on the parameters
    * 
    * @param len
    *            the length of the random string
    * @param dic
    *            the dictionary used to generate the password
    * @return the random password
    */
   public static String generatePassword(int len, String dic) {
	String result = "";
	for (int i = 0; i < len; i++) {
	    int index = random.nextInt(dic.length());
	    result += dic.charAt(index);
	}
	return result;
   }
	
}
