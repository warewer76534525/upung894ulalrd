package com.ocbcmcd.housekeeping.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ocbcmcd.housekeeping.service.IHouseKeepingService;
import com.ocbcmcd.message.EncryptedFileSending;
import com.ocbcmcd.message.OcbcFileProcessFailed;
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;
import com.ocbcmcd.message.OcbcFileSendingFailed;
import com.ocbcmcd.message.SapFileDuplicated;

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
	
	@Value("${failed.dir}")
	private String failedDirectory;

	@Override
	public void moveSuccessFile(OcbcFileProcessedSucessfully event) {
		File processing = getProcessingFile(event.getFileName());
		File outgoing = getOutgoingFile(event.getFileName());
		File encrypted = getEncryptedFile(event.getFileName());
		
		try {
			if (outgoing.exists()) {
				outgoing.delete();
			}
			FileUtils.moveFile(processing, outgoing);
			encrypted.delete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void moveOnProcessingFile(EncryptedFileSending event) {
		File incoming = getIncomingFile(event.getFileName());
		File processing = getProcessingFile(event.getFileName());
		
		moveFile(incoming, processing);
	}
	
	@Override
	public void moveOnDuplicatedFile(SapFileDuplicated event) {
		File incoming = getIncomingFile(event.getFileName());
		File failed = getFailedFile(event.getFileName());
		
		moveFile(incoming, failed);
	}
	
	@Override
	public void moveOnSendingFailed(OcbcFileSendingFailed event) {
		File processing = getProcessingFile(event.getFileName());
		File failed = getFailedFile(event.getFileName());
		File encrypted = getEncryptedFile(event.getFileName());
		
		try {
			if (failed.exists()) {
				failed.delete();
			}
			FileUtils.moveFile(processing, failed);
			encrypted.delete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public void moveOnProcessedFailed(OcbcFileProcessFailed event) {
		File processing = getProcessingFile(event.getFileName());
		File failed = getFailedFile(event.getFileName());
		File encrypted = getEncryptedFile(event.getFileName());
		
		try {
			if (failed.exists()) {
				failed.delete();
			}
			FileUtils.moveFile(processing, failed);
			encrypted.delete();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private void moveFile(File source, File destination) {
		try {
			if (destination.exists()) {
				destination.delete();
			}
			FileUtils.moveFile(source, destination);
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
		return new File(encryptedDirectory, fileName + encryptedExt);
	}

	private File getFailedFile(String fileName) {
		return new File(failedDirectory, fileName);
	}

	

	
}
