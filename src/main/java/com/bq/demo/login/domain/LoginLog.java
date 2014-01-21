package com.bq.demo.login.domain;

import java.io.Serializable;
import java.util.Date;

public class LoginLog implements Serializable {

	private static final long serialVersionUID = -7291732806715302783L;

	private int loginlogID;
	private int userID;
	private String userIP;
	private Date loginDate;

	public int getLoginlogID() {
		return loginlogID;
	}

	public void setLoginlogID(int loginlogID) {
		this.loginlogID = loginlogID;
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getUserIP() {
		return userIP;
	}

	public void setUserIP(String userIP) {
		this.userIP = userIP;
	}

	public Date getLoginDate() {
		return loginDate;
	}

	public void setLoginDate(Date loginDate) {
		this.loginDate = loginDate;
	}

}
