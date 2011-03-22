package com.ocbcmcd.monitoring.query;

import java.util.Date;
import java.util.List;

import com.ocbcmcd.monitoring.domain.LogEvent;

public interface ILogEventQuery {

	List<LogEvent> getLogs();
	List<LogEvent> getLogs(Date startDate, Date endDate);
}
