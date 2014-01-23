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

package bq.spring.mvc.demo.login.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.stereotype.Repository;

import bq.spring.mvc.demo.login.domain.User;

/**
 * <b>  </b>
 *
 * <p> </p>
 *
 * @author Jonathan Q. Bo (jonathan.q.bo@gmail.com)
 *
 * Created at Jan 23, 2014 8:48:34 AM
 *
 */
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
