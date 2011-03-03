package com.ocbcmcd.housekeeping.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ocbcmcd.housekeeping.service.IHouseKeepingService;
import com.ocbcmcd.message.EncryptedFileSending;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.triplelands.common.util.CopyFileUtil;

@Service
public class HouseKeepingService implements IHouseKeepingService {
	
	protected static Log log = LogFactory.getLog(HouseKeepingService.class);
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
	@Value("${incoming.dir}")
	private String incomingDirectory;
	
	@Value("${encrypted.dir}")
	private String encryptedDirectory;
	
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
	public void moveSuccessFile(OcbcFileProcessedSucessfully event) {
		File processing = getProcessingFile(event.getFileName());
		File outgoing = getOutgoingFile(event.getFileName());
		File encrypted = getEncryptedFile(event.getFileName());
		try {
			copyFileUtil.move(processing, outgoing);
			encrypted.delete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void moveOnProcessingFile(EncryptedFileSending event) {
		File incoming = getIncomingFile(event.getFileName());
		File processing = getProcessingFile(event.getFileName());
		
		try {
			copyFileUtil.move(incoming, processing);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	private File getIncomingFile(String fileName) {
		return new File(incomingDirectory, fileName);
	}
	
	private File getProcessingFile(String fileName) {
		return new File(processingDirectory, fileName);
	}
	
	private File getOutgoingFile(String fileName) {
		return new File(outgoingDirectory, fileName);
	}
	
	private File getEncryptedFile(String fileName) {
		return new File(encryptedDirectory, fileName + "." + encryptedExt);
	}
}
