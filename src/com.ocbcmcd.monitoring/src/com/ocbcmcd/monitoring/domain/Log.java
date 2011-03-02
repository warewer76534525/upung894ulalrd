package com.ocbcmcd.monitoring.domain;

import java.util.Date;


public class Log {
	public final static String SUCCESS = "success";
	public final static String ERROR = "error";
	
	private String description;
	private String type;
	private Date time;	
	
	public Log(String description, String type, Date time) {
		super();
		this.description = description;
		this.type = type;
		this.time = time;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
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

}
