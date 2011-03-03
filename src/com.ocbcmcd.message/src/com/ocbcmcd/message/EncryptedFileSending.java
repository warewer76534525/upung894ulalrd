package com.ocbcmcd.message;

import java.io.Serializable;
import java.util.Date;

public class EncryptedFileSending implements Serializable {
<<<<<<< HEAD
private static final long serialVersionUID = -4742193782275583068L;
	
=======
	private static final long serialVersionUID = -4742193782275583068L;

>>>>>>> 6fde31d0766be37f37438ca6df3762fea235161a
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
