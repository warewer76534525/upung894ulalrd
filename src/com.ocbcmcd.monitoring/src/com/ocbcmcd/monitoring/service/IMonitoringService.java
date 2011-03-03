package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.message.OcbcFileProcessedSucessfully;


public interface IMonitoringService {
	void logFileProcessedEvent(OcbcFileProcessedSucessfully event);
}
