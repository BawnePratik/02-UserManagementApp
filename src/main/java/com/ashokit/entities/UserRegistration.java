package com.ashokit.entities;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "USER_REGISTRATION")
public class UserRegistration {

	@Id
	@GeneratedValue
	@Column(name = "USER_ID")
	private Integer userId;
	
	@Column(name = "FIRST_NAME")
	private String fName;
	
	@Column(name = "LAST_NAME")
	private String lName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PHONE_NUMBER")
	private Long phno;
	
	@Temporal(TemporalType.DATE)    //It will take only date, Then time will not come
	@Column(name = "DATE_OF_BIRTH")
	private Date dob;
	
	@Column(name = "GENDER")
	private String gender;
	
	@Column(name = "COUNTRY")
	private String country;
	
	@Column(name = "STATE")
	private String state;
	
	@Column(name = "CITY")
	private String city;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "ACCOUNT_STATUS")
	private String acc_Status;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Long getPhno() {
		return phno;
	}

	public void setPhno(Long phno) {
		this.phno = phno;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getAcc_Status() {
		return acc_Status;
	}

	public void setAcc_Status(String acc_Status) {
		this.acc_Status = acc_Status;
	}

	@Override
	public String toString() {
		return "UserRegistration [userId=" + userId + ", fName=" + fName + ", lName=" + lName + ", email=" + email
				+ ", phno=" + phno + ", dob=" + dob + ", gender=" + gender + ", country=" + country + ", state=" + state
				+ ", city=" + city + ", password=" + password + ", acc_Status=" + acc_Status + "]";
	}
}
