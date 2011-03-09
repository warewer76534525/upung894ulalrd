package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import org.apache.commons.io.FileUtils;

public class FileCacheReader {
	private File file;
	private String text;
	
	public FileCacheReader(File file) {
		this.file = file;
		try {
			readAllText();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public File getFile() {
		return file;
	}
	
	public String readAllText() throws Exception  {
		if (text == null) {
			text = FileUtils.readFileToString(file);
		}
		
		return text;
	}

	public int getLinesCount() {
		return text.split("\\n").length;
	}

}
