package com.ocbcmcd.monitoring.service.impl;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ocbcmcd.message.OcbcFileProcessFailed;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.message.OcbcFileSent;
import com.ocbcmcd.message.SapFileDuplicated;
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
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.PROCESSED, event.getTime(), "");
		return logEvent;
	}
	
	private LogEvent generateSentLogEvent(OcbcFileSent event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.SENT, event.getTime(), "");
		return logEvent;
	}

	@Override
	public void logFileSentEvent(OcbcFileSent event) {
		LogEvent logEvent = generateSentLogEvent(event);
		logEventDao.save(logEvent);
	}

	@Override
	public void logDuplicatedFile(SapFileDuplicated event) {
		LogEvent logEvent = generateDuplicatedFileLogEvent(event);
		logEventDao.save(logEvent);
	}

	private LogEvent generateDuplicatedFileLogEvent(SapFileDuplicated event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.DUPLICATED, event.getTime(), "");
		return logEvent;
	}

	@Override
	public void logSendingFailed(OcbcFileSendingFailed event) {
		LogEvent logEvent = generateSendingFailedLogEvent(event);
		logEventDao.save(logEvent);
	}

	private LogEvent generateSendingFailedLogEvent(OcbcFileSendingFailed event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.SENDING_FAILED, event.getTime(), event.getErrorMessage());
		return logEvent;
	}

	@Override
	public void logProcessedFailed(OcbcFileProcessFailed event) {
		LogEvent logEvent = generateProcessedFailedLogEvent(event);
		logEventDao.save(logEvent);
	}

	private LogEvent generateProcessedFailedLogEvent(OcbcFileProcessFailed event) {
		LogEvent logEvent = new LogEvent(event.getFileName(), LogEvent.PROCESSED_FAILED, event.getTime(), "");
		return logEvent;
	}

}
