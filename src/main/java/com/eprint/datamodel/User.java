package com.eprint.datamodel;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVUser;
import com.avos.avoscloud.LogInCallback;

public class User extends AVUser{
	private transient String m_passcode;
	
	private String m_username;
	
	private String m_email;
	
	public User() {
		super();
	}
	
	public static User create() {
		return new User();
	}
	
	public User username(String username) {
		m_username = username;
		this.setUsername(username);
		return this;
	}

	public User passcode(String code) {
		m_passcode = code;
		this.setPassword(code);
		return this;
	}
	
	public User emailAddress(String email) {
		m_email = email;
		this.setEmail(email);
		return this;
	}
	
	public String username() {
		return m_username;
	}
	
	public String emailAddress() {
		return m_email;
	}
	
	public User login() 
			throws AVException {
	    return AVUser.logIn(m_username, m_passcode, User.class);
	}
	
	public void login(LogInCallback<User> cb) {
		AVUser.logInInBackground(m_username, m_passcode, cb, User.class);;
	}
	
	public String sessionToken() {
		return this.getSessionToken();
	}
}
