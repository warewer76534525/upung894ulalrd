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
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(fileReader.getFile());
			String checksum = DigestUtils.md5Hex(fis);
			return checksum;
		} catch (Exception e) {
			throw new RuntimeException(e);
		} finally {
			try { fis.close(); } catch (Exception e) {}
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
