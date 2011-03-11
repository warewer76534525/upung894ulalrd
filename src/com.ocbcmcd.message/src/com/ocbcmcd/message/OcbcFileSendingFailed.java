package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class OcbcFileSendingFailed implements Serializable {

	private static final long serialVersionUID = -6420353611811826233L;

	private String fileName;
	private Date time = new Date();
	private String errorMessage;
	private String stackTrace;

	public OcbcFileSendingFailed(String fileName, String errorMessage,
			String stackTrace) {
		this.fileName = fileName;
		this.errorMessage = errorMessage;
		this.stackTrace = stackTrace;
	}

	public String getFileName() {
		return fileName;
	}

	public Date getTime() {
		return time;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public String getStackTrace() {
		return stackTrace;
	}

	@Override
	public String toString() {
		return "OcbcFileProcessedSucessfully [fileName=" + fileName + ", time="
				+ time + "]";
	}
}
