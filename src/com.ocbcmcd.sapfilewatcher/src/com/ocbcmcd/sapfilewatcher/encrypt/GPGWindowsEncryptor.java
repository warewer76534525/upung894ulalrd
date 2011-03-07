package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Component;

@Component
public class GPGWindowsEncryptor {
	protected Log log = LogFactory.getLog(getClass());
	
	public File encrypt(String keyId, File originalFile) throws Exception {
		File encryptedFile = new File(originalFile.getParent(), originalFile.getName() + ".gpg"); 
		
		if (encryptedFile.exists()) {
			encryptedFile.delete();
			log.info("delete previous encrypted file");
		}
		
		String line = String.format("gpg -r %s -e %s", keyId, originalFile.getAbsolutePath()); 
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		
		int exitValue = executor.execute(cmdLine);
		
		log.info("Exit value of gpg : " + exitValue);
		
		return encryptedFile;
	}
}
