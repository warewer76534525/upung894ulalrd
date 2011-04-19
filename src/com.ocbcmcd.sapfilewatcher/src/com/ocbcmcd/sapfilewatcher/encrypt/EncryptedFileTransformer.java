package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.PrintWriter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class EncryptedFileTransformer {
	
	private static final char NEW_LINE = '\n';
	
	@Value("${encrypted.dir}")
	private String encryptedFilePath;
	
	@Value("${public.key.id}")
	private String publicKeyId;
	
	@Autowired
	private GPGWindowsEncryptor encryptor;
	
	public File create(File original) throws Exception {
		File tempAppendedHeaderFile = new File(encryptedFilePath, original.getName());
		
		PrintWriter writer = new PrintWriter(tempAppendedHeaderFile);
		
		FileInformation fileInformation = new FileInformation(original);
		
		writer.print(fileInformation.getHeader());
		
		writer.print(NEW_LINE);
		
		writer.print(fileInformation.getContent());
		
		writer.close();

		File encryptedFile = encryptor.encrypt(publicKeyId, tempAppendedHeaderFile);
		
		tempAppendedHeaderFile.delete();
		
		return encryptedFile;
	}
}
