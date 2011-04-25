package com.ocbcmcd.housekeeping.service.impl;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ocbcmcd.housekeeping.service.IDirectoryCleaner;

@Service
public class DirectoryCleaner implements IDirectoryCleaner {
	protected Log log = LogFactory.getLog(getClass());
	
	private final static String START_STATUS = "1";
	
	@Value("${failed.dir}")
	private String failedDirectory;
	
	@Value("${outgoing.dir}")
	private String outgoingDirectory;
	
	@Value("${endOfMonth.start}")
	private String startStatus;

	@Override
	public void clean() throws IOException {
		if (isStart()) {
			FileUtils.cleanDirectory(new File(failedDirectory));
			FileUtils.cleanDirectory(new File(outgoingDirectory));
		} else {
			log.info("Clean directory status is stop");
		}
	}
	
	private boolean isStart() {
		return startStatus.equals(START_STATUS);
	}
}
