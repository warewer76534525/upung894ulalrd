package com.ocbcmcd.config.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class PropertyService {
	private List<String> locations = new ArrayList<String>();
	private Properties masterProperties;

	public PropertyService(String parentLocation, String[] servicesName,
			String configName, String masterConfigLocation) {
		for (String service : servicesName) {
			locations.add(parentLocation + service + "/" + configName);
		}

		masterProperties = loadPropertiesFromFile(masterConfigLocation);
	}

	private Properties loadPropertiesFromFile(String masterConfigLocation) {
		Properties masterProperties = new Properties();
		try {
			masterProperties.load(new FileInputStream(masterConfigLocation));
			return masterProperties;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public void changeAllConfigValues() {
		for (String location : locations) {
			System.out.println(location);
			changeAllValuesInConfigWith(location);
		}
	}

	private void changeAllValuesInConfigWith(String location) {
		Properties applicationConfig = loadPropertiesFromFile(location);
		for (Object key : applicationConfig.keySet()) {
			changeKeyValueFor(applicationConfig, key);
		}
		
		try {
			applicationConfig.store(new FileOutputStream(location), "");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void changeKeyValueFor(Properties applicationConfig, Object key) {
		if (!masterProperties.containsKey(key)) return;
		System.out.println("Change value for : " + key);
		
		applicationConfig.setProperty(key.toString(), masterProperties.getProperty(key.toString()));
	}
}
