package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSent;


public interface IMonitoringService {
	void logFileProcessedEvent(OcbcFileProcessedSucessfully event);
	void logFileSentEvent(OcbcFileSent event);
}
