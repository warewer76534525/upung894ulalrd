package com.ocbcmcd.message;

import java.util.Date;

public class SapFileEncrypted {
	private String fileName;
	private Date time = new Date();
	
	public SapFileEncrypted(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public Date getTime() {
		return time;
	}
	
}
