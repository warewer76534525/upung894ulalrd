package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class OcbcFileProcessFailed implements Serializable {
	
	private static final long serialVersionUID = -6420353611811826233L;
	
	private String fileName;
	private Date time = new Date();
	
	public OcbcFileProcessFailed(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public Date getTime() {
		return time;
	}

	@Override
	public String toString() {
		return "OcbcFileProcessFailed [fileName=" + fileName + ", time="
				+ time + "]";
	}
	
	
}
