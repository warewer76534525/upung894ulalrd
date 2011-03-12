package com.ocbcmcd.sapfilewatcher.duplicatechecker;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Component;

import com.ocbcmcd.sapfilewatcher.encrypt.DateString;

@Component
public class FileStorage {
	private File storageFile;
	private List<String> fileContents;
	
	public void prepareStorage() {
		storageFile = new File("file-sent-today.txt");
		
		try {
			if (!storageFile.exists()) {
				storageFile.createNewFile();
				
				List<String> contents = new ArrayList<String>();
				
				contents.add(new DateString().toString());
				contents.add("[]");
				
				FileUtils.writeLines(storageFile, contents);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void resetStorage() {
		storageFile.delete();
		prepareStorage();
	}
	
	public void reloadFromStorage() throws IOException {
		fileContents = FileUtils.readLines(storageFile);	
		
		if (isContentAlreadyExpired()) {
			resetStorage();
		}
	}

	public boolean isContentAlreadyExpired() {
		return !getStorageDate().equals(new DateString().toString());
	}

	public String getStorageDate() {
		return fileContents.get(0);
	}

	public String getJsonContent() {
		return fileContents.get(1);
	}

	public void saveJsonContent(String json) {
		try {
			List<String> contents = new ArrayList<String>();
			
			contents.add(new DateString().toString());
			contents.add(json);
			
			FileUtils.writeLines(storageFile, contents);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
