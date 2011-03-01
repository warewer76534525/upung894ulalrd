package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class FileInformation {
	private File original;
	
	public FileInformation(File original) {
		this.original = original;
	}

	public String getHeader() {
		try {
			return calculateCheckSum();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return null;
	}
	
	private String calculateCheckSum() throws Exception {
		return DigestUtils.md5Hex(new FileInputStream(original));
	}

	public String getContent() throws Exception {
		FileInputStream reader = new FileInputStream(original);
		
		byte[] content = new byte[reader.available()];
		
		reader.read(content);
		reader.close();
		
		return new String(content);
	}
}
