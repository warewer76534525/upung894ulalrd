package com.ocbcmcd.monitoring.dao;

import com.ocbcmcd.monitoring.domain.LogEvent;

public interface ILogEventDao {

	void save(LogEvent logEvent);

}
