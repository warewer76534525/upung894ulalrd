package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.FileInputStream;
import java.io.PrintWriter;

public class EncryptedFileTransformer {
	private File original;
	
	public EncryptedFileTransformer(File original) {
		this.original = original;
	}
	
	public void create() throws Exception {
		File file = new File("C:\\erpfile\\encrypted\\", original.getName());
		
		PrintWriter writer = new PrintWriter(file);
		
		writer.println(getHeader());
		
		writer.println(getContent());
		
		writer.close();
	}

	private String getHeader() {
		return "checksum";
	}

	private String getContent() throws Exception {
		FileInputStream reader = new FileInputStream(original);
		byte[] content = new byte[reader.available()];
		reader.read(content);
		reader.close();
		return new String(content);
	}
}
