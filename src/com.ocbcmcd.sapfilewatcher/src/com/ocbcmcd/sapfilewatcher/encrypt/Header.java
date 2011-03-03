package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.FileInputStream;

import org.apache.commons.codec.digest.DigestUtils;

public class Header {
	private FileCacheReader fileReader;
	
	public Header(File file) {
		this.fileReader = new FileCacheReader(file);
	}
	
	public Header(FileCacheReader fileReader) {
		this.fileReader = fileReader;
	}

	private String calculateCheckSum() {
		try {
			return DigestUtils.md5Hex(new FileInputStream(fileReader.getFile()));	
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	@Override
	public String toString() {
		return String.format("%s;%d;%s", calculateCheckSum(), countRow() ,new DateString());
	}

	private int countRow() {
		return fileReader.getLinesCount();
	}
}
