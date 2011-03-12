package com.ocbcmcd.sapfilewatcher.duplicatechecker;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ErpFileDuplicationGuard {
	@Autowired
	private TodayFileSentStorage todayFileSentStorage;

	public void ensureFileNotDuplicate(File file) {
		if (isFileAlreadySent(file)) {
			throw new FileAlreadySentException(file.getName());
		} else {
			 todayFileSentStorage.saveNewFile(file.getName());
		}
	}

	private boolean isFileAlreadySent(File file) {
		return todayFileSentStorage.exists(file.getName());
	}
}
