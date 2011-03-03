package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

public class FileInformation {
	
	private FileCacheReader fileReader;
	
	public FileInformation(File original) {
		this.fileReader = new FileCacheReader(original);
	}

	public String getHeader() {
		return new Header(fileReader).toString();
	}

	public String getContent() throws Exception {	
		return fileReader.readAllText();
	}
}
