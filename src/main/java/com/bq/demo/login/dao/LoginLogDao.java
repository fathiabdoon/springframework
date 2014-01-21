package com.bq.demo.login.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.bq.demo.login.domain.LoginLog;

@Repository
public class LoginLogDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	public void insertLoginLog(LoginLog log){
		String sql = "insert into t_login_log(userid, visitip, visitdate) values(?,?,?)";
		jdbcTemplate.update(sql, log.getUserID(),log.getUserIP(), log.getLoginDate());
	}
}
