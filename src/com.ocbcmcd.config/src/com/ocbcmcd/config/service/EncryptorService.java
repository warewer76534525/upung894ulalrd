package com.ocbcmcd.config.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncryptorService {
	private List<String> locations = new ArrayList<String>();
	private List<String> encryptedKeyLists;
	
	public EncryptorService(String parentLocation, String[] servicesName,
			String configName, String[] encryptedKeyList) {
		for (String service : servicesName) {
			locations.add(parentLocation + service + "/" + configName);
		}
		
		this.encryptedKeyLists = Arrays.asList(encryptedKeyList);
	}

	public void encryptAllValues() {
		for (String location : locations) {
			encryptAllValuesInConfig(location);
		}
	}

	private void encryptAllValuesInConfig(String location) {
		EncryptedConfigurationService singleEncryptorService = new EncryptedConfigurationService(location, encryptedKeyLists);
		singleEncryptorService.encryptSecretPropertyValues();
	}
}
