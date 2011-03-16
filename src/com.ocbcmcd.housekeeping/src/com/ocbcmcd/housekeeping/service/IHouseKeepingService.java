package com.ocbcmcd.housekeeping.service;

import com.ocbcmcd.message.EncryptedFileSending;
import com.ocbcmcd.message.OcbcFileProcessFailed;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.message.SapFileDuplicated;

public interface IHouseKeepingService {

	void moveSuccessFile(OcbcFileProcessedSucessfully fileProcessedEvent);
	void moveOnProcessingFile(EncryptedFileSending event);
	void moveOnDuplicatedFile(SapFileDuplicated event);
	void moveOnSendingFailed(OcbcFileSendingFailed event);
	void moveOnProcessedFailed(OcbcFileProcessFailed event);;
	
}
