package com.ocbcmcd.housekeeping.service;

import com.ocbcmcd.message.OcbcFileProcessedSucessfully;

public interface IHouseKeepingService {

	void moveSuccessFile(OcbcFileProcessedSucessfully fileProcessedEvent);

}
