package com.ocbcmcd.confirmwatcher.checker;

import java.io.File;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChkConfirmationChecker {
	protected Log log = LogFactory.getLog(getClass());
	
	@Autowired
	private FTPFileChecker fileChecker;
	
	public ConfirmationStatus getStatus(String fileName) throws Exception {
		File originalFile = new File(fileName);
		String chkFileName = originalFile.getName().replaceAll(".txt.gpg", ".chk");
		
		log.info("chk file name : " + chkFileName);
		
		if (fileChecker.fileExist(chkFileName)) {
			fileChecker.deleteFile(chkFileName);
			return ConfirmationStatus.Success;
		}
		
		fileChecker.close();
		
		return ConfirmationStatus.Unavailable;
	}
}
