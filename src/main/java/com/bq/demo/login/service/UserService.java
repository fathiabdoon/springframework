package com.bq.demo.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.bq.demo.login.dao.LoginLogDao;
import com.bq.demo.login.dao.UserDao;
import com.bq.demo.login.domain.LoginLog;
import com.bq.demo.login.domain.User;

@Service
public class UserService {

	@Autowired
	private UserDao userDao;
	@Autowired
	private LoginLogDao logDao;
	
	public boolean canLogin(String userName, String password){
		return userDao.canLogin(userName, password);
	}
	
	public User findUser(String userName){
		return userDao.findUserByName(userName);
	}
	
	public void loginSuccess(User user){
		user.setCredits(5+user.getCredits());
		
		LoginLog log = new LoginLog();
		log.setUserID(user.getUserID());
		log.setUserIP(user.getLastIP());
		log.setLoginDate(user.getLastVisitDate());

		userDao.updateUserLoginInfo(user);
		logDao.insertLoginLog(log);
	}
	
}
