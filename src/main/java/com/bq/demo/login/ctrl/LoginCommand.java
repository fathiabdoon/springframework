package com.bq.demo.login.ctrl;

import java.io.Serializable;

public class LoginCommand implements Serializable {

	private static final long serialVersionUID = -7993335911428799476L;

	private String userName;
	private String userPassword;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getUserPassword() {
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

}
