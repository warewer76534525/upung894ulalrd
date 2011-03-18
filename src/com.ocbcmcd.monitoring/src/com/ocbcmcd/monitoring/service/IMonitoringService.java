package com.ocbcmcd.monitoring.service;

import com.ocbcmcd.message.OcbcFileProcessFailed;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.message.OcbcFileSent;
import com.ocbcmcd.message.SapFileDuplicated;


public interface IMonitoringService {
	void logFileProcessedEvent(OcbcFileProcessedSucessfully event);
	void logFileSentEvent(OcbcFileSent event);
	void logDuplicatedFile(SapFileDuplicated event);
	void logSendingFailed(OcbcFileSendingFailed event);
	void logProcessedFailed(OcbcFileProcessFailed event);
}
