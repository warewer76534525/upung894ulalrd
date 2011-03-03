package com.ocbcmcd.monitoring.query;

import java.util.List;

import com.ocbcmcd.monitoring.domain.LogEvent;

public interface ILogEventQuery {

	List<LogEvent> getLogs();

}
