package com.ocbcmcd.monitoring.query;

import java.util.Date;
import java.util.List;

import com.ocbcmcd.monitoring.command.LogSearchCommand;
import com.ocbcmcd.monitoring.domain.LogEvent;

public interface ILogEventQuery {

	List<LogEvent> getLogs(LogSearchCommand command);
	List<LogEvent> getLogs(Date startDate, Date endDate);
	List<LogEvent> getLogs();
}
