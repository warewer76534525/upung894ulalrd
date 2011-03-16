package com.ocbcmcd.monitoring.dao.impl;

import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.ocbcmcd.monitoring.dao.ILogEventDao;
import com.ocbcmcd.monitoring.domain.LogEvent;

@Repository
public class LogEventDao implements ILogEventDao {
	protected final Log log = LogFactory.getLog(getClass());

	private SimpleJdbcInsert insertLogEventActor;

	@Autowired
	public void setDataSource(DataSource dataSource) {
        this.insertLogEventActor =
                new SimpleJdbcInsert(dataSource)
			        .withTableName("log_event")
					.usingColumns("file_name", "type", "date", "description")
					.usingGeneratedKeyColumns("id");

	}

	@Override
	public void save(LogEvent logEvent) {
		Map<String, Object> parameters = new HashMap<String, Object>(3);
		parameters.put("file_name", logEvent.getFileName());
		parameters.put("type", logEvent.getType());
		parameters.put("date", logEvent.getTime());
		parameters.put("description", logEvent.getDescription());
		insertLogEventActor.executeAndReturnKey(parameters);
	}

}
