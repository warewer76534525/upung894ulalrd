package com.ocbcmcd.monitoring.query.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocbcmcd.monitoring.command.UserSearchCommand;
import com.ocbcmcd.monitoring.domain.User;
import com.ocbcmcd.monitoring.query.IUserQuery;

@Service
public class UserQuery extends SimpleJdbcDaoSupport implements IUserQuery {
	protected final Log log = LogFactory.getLog(getClass());
	
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;
	
	@Autowired()
	public UserQuery(DataSource dataSource) {
		super.setDataSource(dataSource);
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
	}

	@Override
	public List<User> getUsers() {
		String sql = "SELECT * FROM users u ORDER BY u.user_name";
		return getJdbcTemplate().query(sql, new UserMapper());
	}
	
	@Override
	public List<User> getUsers(UserSearchCommand command) {
		String select = " SELECT * FROM users u ";
		String orderBy = " ORDER BY u.user_name "; 
		String where = "WHERE 1=1 ";
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		if (!StringUtils.isBlank(command.getUserName())) {
			where += " AND u.user_name LIKE :user_name ";
			namedParameters.put("user_name", "%" + command.getUserName() + "%");
		}
		
		String sql = select + where + orderBy;
				
		return namedParameterJdbcTemplate.query(sql, namedParameters, new UserMapper());
		
	}

	private static class UserMapper implements RowMapper<User> {

		@Override
		public User mapRow(java.sql.ResultSet rs, int rowNum)
				throws SQLException {
			Timestamp createdDate = rs.getTimestamp("created_date");
			long milliseconds = createdDate.getTime() + (createdDate.getNanos() / 1000000);
			User user = new User();
			
			user.setId(rs.getInt("id"));
			user.setUserName(rs.getString("user_name"));
			user.setPassword(rs.getString("password"));
			user.setEnabled(rs.getInt("enabled"));
			user.setCreatedDate(new java.util.Date(milliseconds));
			
			return user;
		}
	}

	

}
