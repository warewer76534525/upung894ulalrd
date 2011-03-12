package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class SapFileDuplicated implements Serializable {
	
	private static final long serialVersionUID = -4742193782279383068L;
	
	private String fileName;
	
	private Date time = new Date();
	
	public SapFileDuplicated(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}
	
	public Date getTime() {
		return time;
	}
	
}
