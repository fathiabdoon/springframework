/*
Copyright (c) 2014 (Jonathan Q. Bo) 

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

The Software shall be used for Good, not Evil.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
*/

package bq.spring.mvc.demo.login.service;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import bq.spring.mvc.demo.login.dao.LoginLogDao;
import bq.spring.mvc.demo.login.dao.UserDao;
import bq.spring.mvc.demo.login.domain.LoginLog;
import bq.spring.mvc.demo.login.domain.User;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 23, 2014 9:38:59 AM
 *
 */
@Service
public class UserService {

	private static Logger logger = Logger.getLogger(UserService.class);
	
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
		logger.debug("user login successfully!");
		
		user.setCredits(5+user.getCredits());
		
		LoginLog log = new LoginLog();
		log.setUserID(user.getUserID());
		log.setUserIP(user.getLastIP());
		log.setLoginDate(user.getLastVisitDate());

		userDao.updateUserLoginInfo(user);
		logger.debug("update user login info");
		
		logDao.insertLoginLog(log);
		logger.debug("write login log");
	}
	
}
