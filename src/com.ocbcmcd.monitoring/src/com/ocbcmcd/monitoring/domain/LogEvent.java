package com.ocbcmcd.monitoring.domain;

import java.util.Date;


public class LogEvent {
	public final static String SENT = "SENT";
	public final static String ERROR = "ERROR";
	public final static String PROCESSED = "PROCESSED";
	public final static String FAILED = "FAILED";
	
	private String fileName;
	private String type;
	private Date time;	
	
	public LogEvent(String description, String type, Date time) {
		super();
		this.fileName = description;
		this.type = type;
		this.time = time;
	}

	public String getFileName() {
		return fileName;
	}

	public void setDescription(String description) {
		this.fileName = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	@Override
	public String toString() {
		return "LogEvent [description=" + fileName + ", type=" + type
				+ ", time=" + time + "]";
	}
	
	

}
