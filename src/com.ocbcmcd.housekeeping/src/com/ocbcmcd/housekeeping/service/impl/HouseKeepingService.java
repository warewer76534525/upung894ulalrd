package com.ocbcmcd.housekeeping.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ocbcmcd.housekeeping.service.IHouseKeepingService;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.triplelands.common.util.CopyFileUtil;

@Service
public class HouseKeepingService implements IHouseKeepingService {
	
	protected static Log log = LogFactory.getLog(HouseKeepingService.class);
	
	@Value("${processing.dir}")
	private String processingDirectory;

	@Value("${outgoing.dir}")
	private String outgoingDirectory;

	private CopyFileUtil copyFileUtil;

	public HouseKeepingService() {
		copyFileUtil = new CopyFileUtil();
	}

	public void setProcessingDirectory(String processingDirectory) {
		this.processingDirectory = processingDirectory;
	}

	public void setOutgoingDirectory(String outgoingDirectory) {
		this.outgoingDirectory = outgoingDirectory;
	}

	@Override
	public void moveSuccessFile(OcbcFileProcessedSucessfully fileProcessedEvent) {
		File processing = new File(processingDirectory,
				fileProcessedEvent.getFileName());
		File outgoing = new File(outgoingDirectory,
				fileProcessedEvent.getFileName());
		
		try {
			copyFileUtil.copy(processing, outgoing);
			processing.delete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
}
