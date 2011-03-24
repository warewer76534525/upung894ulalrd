package com.ocbcmcd.monitoring.query.impl;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
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

import com.ocbcmcd.monitoring.command.LogSearchCommand;
import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.query.ILogEventQuery;

@Service
public class LogEventQuery extends SimpleJdbcDaoSupport implements ILogEventQuery {
	protected final Log log = LogFactory.getLog(getClass());
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	@Autowired()
	public LogEventQuery(DataSource dataSource) {
		super.setDataSource(dataSource);
	    this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
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
	
	@Override
	public List<LogEvent> getLogs(LogSearchCommand command) {
		String select = " SELECT * FROM log_event le ";
		String orderBy = " ORDER BY le.date desc "; 
		String where = "WHERE 1=1 ";
		
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		
		if (!StringUtils.isBlank(command.getFile())) {
			where += " AND le.file_name LIKE :file_name ";
			namedParameters.put("file_name", "%" + command.getFile() + "%");
		}
		
		if (command.getStartDate() != null && command.getEndDate() != null) {
			where += " AND le.date BETWEEN :start_date AND :end_date ";
			namedParameters.put("start_date", command.getStartDate());
			
			Calendar cTo = Calendar.getInstance();
			cTo.setTime(command.getEndDate());
			cTo.add(Calendar.DATE, 1);
			namedParameters.put("end_date", cTo.getTime());
		}
		

		String sql = select + where + orderBy;
				
		return namedParameterJdbcTemplate.query(sql, namedParameters, new LogEventMapper());
		
	}
	
	@Override
	public List<LogEvent> getLogs(Date startDate, Date endDate) {
		String sql = "SELECT * FROM log_event le WHERE le.date BETWEEN :start_date AND :end_date ORDER BY le.date desc";
		Map<String, Object> namedParameters = new HashMap<String, Object>();
		namedParameters.put("start_date", startDate);
		namedParameters.put("end_date", endDate);
		
		return namedParameterJdbcTemplate.query(sql, namedParameters, new LogEventMapper());
	}

}
