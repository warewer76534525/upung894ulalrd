package com.ocbcmcd.monitoring.query.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcDaoSupport;
import org.springframework.stereotype.Service;

import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.query.ILogEventQuery;

@Service
public class LogEventQuery extends SimpleJdbcDaoSupport implements
		ILogEventQuery {
	protected final Log log = LogFactory.getLog(getClass());

	@Autowired()
	public LogEventQuery(DataSource dataSource) {
		super.setDataSource(dataSource);
	}

	@Override
	public List<LogEvent> getLogs() {
		String sql = "SELECT * FROM log_event le ORDER BY le.date desc";
		return getJdbcTemplate().query(sql, new LogEventMapper());
	}

	private static class LogEventMapper implements RowMapper<LogEvent> {

		@Override
		public LogEvent mapRow(java.sql.ResultSet rs, int rowNum)
				throws SQLException {
			Timestamp timestamp = rs.getTimestamp("date");
			long milliseconds = timestamp.getTime() + (timestamp.getNanos() / 1000000);
			LogEvent logEvent = new LogEvent(rs.getString("file_name"),
					rs.getString("type"), new java.util.Date(milliseconds), rs.getString("description"));
			
			return logEvent;
		}
	}
}
