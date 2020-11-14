package com.michiko.pw.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name="user_accounts")
public class UserAccount {
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator="user_accounts_seq")
	@SequenceGenerator(name = "user_accounts_seq", allocationSize=1)
	@Column(name="user_id")
	private long userId;
	
	@Column(name="username")  // the actual name of the column of db
	//@NotBlank(message="Must give a username")
	//@Size(min=2, max=50)   
	private String userName;
	
	//@NotBlank(message="Must give an email address")
	//@Email(message="Must be a valid email address")
	private String email;
	
	@Column(name="password") 
	//@NotBlank(message="Must give a password")
	//@Size(min=2, max=50)  
	private String password;
	
	private boolean enabled=true;
	
	public UserAccount() {
		
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}
	
}
