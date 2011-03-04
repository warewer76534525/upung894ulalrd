package com.ocbcmcd.monitoring.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSent;
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
		LogEvent logEvent = generateProcessedLogEvent(event);
		log.info(logEvent);
		logEventDao.save(logEvent);
	}
	
	private LogEvent generateProcessedLogEvent(OcbcFileProcessedSucessfully event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.PROCESSED, event.getTime());
		return logEvent;
	}
	
	private LogEvent generateSentLogEvent(OcbcFileSent event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.SENT, event.getTime());
		return logEvent;
	}

	@Override
	public void logFileSentEvent(OcbcFileSent event) {
		LogEvent logEvent = generateSentLogEvent(event);
		log.info(logEvent);
		logEventDao.save(logEvent);
	}

}
