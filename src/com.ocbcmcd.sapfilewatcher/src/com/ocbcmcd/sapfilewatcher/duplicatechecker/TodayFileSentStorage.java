package com.ocbcmcd.sapfilewatcher.duplicatechecker;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

@Component
public class TodayFileSentStorage {
	private Set<String> fileNames = new HashSet<String>();
	private Gson serializer = new Gson();
	private Type setType = new TypeToken<Set<String>>() {}.getType();
	
	private FileStorage fileStorage = new FileStorage();
	
	public TodayFileSentStorage() {
		loadAlreadySentFileFromStorage();
	}
	
	private void loadAlreadySentFileFromStorage() {
		fileStorage.prepareStorage();
		
		try {
			fileNames = reloadFromStorage();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private Set<String> reloadFromStorage() throws IOException {
		
		fileStorage.reloadFromStorage();
		
		synchronizeContentsForToday();
		
		String jsonContent = fileStorage.getJsonContent();
		
		if (jsonContent == null || jsonContent.equals("")) 
			return new HashSet<String>();
		
		return serializer.fromJson(jsonContent, setType);
	}

	private void synchronizeContentsForToday() {
		if (fileStorage.isContentAlreadyExpired()) {
			fileStorage.resetStorage();
			resetMemoryStorage();
		}
	}

	private void resetMemoryStorage() {
		fileNames.clear();
	}

	public void saveNewFile(String name) {
		synchronizeContentsForToday();
		
		fileNames.add(name);
		
		fileStorage.saveJsonContent(serializer.toJson(fileNames, setType));
	}

	public boolean exists(String name) {
		return fileNames.contains(name);
	}
}
