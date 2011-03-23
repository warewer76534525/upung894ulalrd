package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.FileInputStream;

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
			//text = FileUtils.readFileToString(file);
			FileInputStream reader = new FileInputStream(file);

			byte[] content = new byte[reader.available()];

			reader.read(content);
			reader.close();
			
			text = new String(content);	
		}
		
		return text;
	}

	public int getLinesCount() {
		return text.split("\\n").length;
	}

}
