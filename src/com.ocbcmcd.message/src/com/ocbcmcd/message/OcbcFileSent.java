package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class OcbcFileSent implements Serializable {
	
	private static final long serialVersionUID = -6422628080620165997L;
	
	private String fileName;
	private Date time = new Date();
	
	public OcbcFileSent(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public Date getTime() {
		return time;
	}
}
