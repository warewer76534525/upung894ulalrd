package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class EncryptedFileSending implements Serializable {

	private static final long serialVersionUID = -4742193782275583068L;

	private String fileName;
	private Date time = new Date();

	public EncryptedFileSending(String fileName) {
		this.fileName = fileName;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getTime() {
		return time;
	}
}
