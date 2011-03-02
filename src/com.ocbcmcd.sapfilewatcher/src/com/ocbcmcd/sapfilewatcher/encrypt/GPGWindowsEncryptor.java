package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.springframework.stereotype.Component;

@Component
public class GPGWindowsEncryptor {

	public File encrypt(String keyId, File originalFile) throws Exception {
		File encryptedFile = new File(originalFile.getParent(), originalFile.getName() + ".gpg"); 
		
		if (encryptedFile.exists()) {
			encryptedFile.delete();
			System.out.println("delete previous encrypted file");
		}
		
		String line = String.format("gpg -r %s -e %s", keyId, originalFile.getAbsolutePath()); 
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		
		int exitValue = executor.execute(cmdLine);
		
		System.out.println(exitValue);
		
		System.out.println(encryptedFile.exists());
		
		return encryptedFile;
	}
}
