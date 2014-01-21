package com.bq.demo.login.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import com.bq.demo.login.domain.User;

@Repository
public class UserDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@SuppressWarnings("deprecation")
	public boolean canLogin(String userName, String password){
		String sql = "select count(*) from t_user where username=? and password=?";
		int count = jdbcTemplate.queryForInt(sql,userName, password);
		if(count == 1)
			return true;
		
		return false;
	}
	
	public User findUserByName(String userName){
		String sql = "select * from t_user where username=?";
		final User user = new User();
		jdbcTemplate.query(sql, new Object[]{userName},new RowCallbackHandler(){

			@Override
			public void processRow(ResultSet rs) throws SQLException {
				user.setUserID(rs.getInt("userid"));
				user.setUserName(rs.getString("username"));
				user.setCredits(rs.getInt("credits"));
				user.setLastIP(rs.getString("lastvisitip"));
				user.setLastVisitDate(rs.getDate("lastvisitdate"));
			}
			
		});
		
		return user;
	}
	
	public void updateUserLoginInfo(User user){
		String sql = "update t_user set lastvisitip=?, lastvisitdate=? where userid=?";
		jdbcTemplate.update(sql, user.getLastIP(),user.getLastVisitDate(), user.getUserID());
	}
}
