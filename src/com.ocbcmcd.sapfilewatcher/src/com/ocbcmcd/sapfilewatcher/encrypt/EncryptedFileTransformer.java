package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.PrintWriter;

public class EncryptedFileTransformer {
	
	public void create(File original) throws Exception {
		File tempAppendedHeaderFile = new File("C:\\erpfile\\encrypted\\", original.getName());
		
		PrintWriter writer = new PrintWriter(tempAppendedHeaderFile);
		
		FileInformation fileInformation = new FileInformation(original);
		
		writer.println(fileInformation.getHeader());
		
		writer.println(fileInformation.getContent());
		
		writer.close();
	}
}
