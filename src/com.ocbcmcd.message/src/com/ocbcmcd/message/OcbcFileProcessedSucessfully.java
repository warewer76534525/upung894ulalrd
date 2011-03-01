package com.ocbcmcd.message;

import java.util.Date;

public class OcbcFileProcessedSucessfully {
	private String fileName;
	private Date time = new Date();
	
	public OcbcFileProcessedSucessfully(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public Date getTime() {
		return time;
	}
}
