package com.ocbcmcd.confirmwatcher.checker;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ChkConfirmationChecker {
	
	@Autowired
	private FTPFileChecker fileChecker;
	
	public ConfirmationStatus getStatus(String fileName) throws Exception {
		File originalFile = new File(fileName);
		String chkFileName = originalFile.getName().replaceAll(".txt.gpg", ".chk");
		System.out.println("chk file name : " + chkFileName);
		
		if (fileChecker.fileExist(chkFileName)) {
			fileChecker.deleteFile(chkFileName);
			return ConfirmationStatus.Success;
		}
		
		fileChecker.close();
		
		return ConfirmationStatus.Unavailable;
	}
}
