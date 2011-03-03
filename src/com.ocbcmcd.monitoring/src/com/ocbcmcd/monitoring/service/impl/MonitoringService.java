package com.ocbcmcd.monitoring.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.monitoring.dao.ILogEventDao;
import com.ocbcmcd.monitoring.domain.LogEvent;
import com.ocbcmcd.monitoring.service.IMonitoringService;

@Service
public class MonitoringService implements IMonitoringService {
	protected final Log log = LogFactory.getLog(getClass());
	
	@Autowired
	ILogEventDao logEventDao;
	
	@Override
	public void logFileProcessedEvent(OcbcFileProcessedSucessfully event) {
		LogEvent logEvent = generateSuccessLogEvent(event);
		log.info(logEvent);
		logEventDao.save(logEvent);
	}

	private LogEvent generateSuccessLogEvent(OcbcFileProcessedSucessfully event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.SUCCESS, event.getTime());
		return logEvent;
	}

}
