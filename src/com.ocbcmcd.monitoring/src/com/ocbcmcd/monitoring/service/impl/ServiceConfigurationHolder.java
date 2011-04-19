package com.ocbcmcd.monitoring.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.ocbcmcd.config.service.EncryptedConfigurationService;

public class ServiceConfigurationHolder {
	private List<EncryptedConfigurationService> configurationServices = new ArrayList<EncryptedConfigurationService>();
	protected Log log = LogFactory.getLog(getClass());
	
	public ServiceConfigurationHolder(List<EncryptedConfigurationService> configurationServices) {
		this.configurationServices = configurationServices;
	}
	
	public Map<String, String> getProperties(List<String> ftpConfigList) {
		HashMap<String, String> map = new HashMap<String, String>();
		for (String config : ftpConfigList) {
			String configValue = getProperty(config);
			if (configValue != null) {
				map.put(config, configValue);
			}
		}
		
		return map;
	}
	
	public String getProperty(String config) {
		String configValue = null;
		for (EncryptedConfigurationService encryptedConfigurationService : configurationServices) {
			String str = encryptedConfigurationService.getProperty(config);
			if (str != null) {
				configValue = str;
				break;
			}
		}
		
		return configValue;
	}
	
	public void setProperty(String configName, String configValue) {
		
		for (EncryptedConfigurationService encryptedConfigurationService : configurationServices) {
			encryptedConfigurationService.setProperty(configName, configValue);
		}
		
	}
	
	public void setProperties(Map<String, String> configMap) {
		Iterator<String> iterator = configMap.keySet().iterator();
		
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = configMap.get(key);
			
			setProperty(key, value);
		}
	}

	public void setEncryptedProperties(Map<String, String> configMap) {
		Iterator<String> iterator = configMap.keySet().iterator();
		
		while (iterator.hasNext()) {
			String key = (String) iterator.next();
			String value = configMap.get(key);
			
			setAndEncryptProperty(key, value);
		}
	}

	private void setAndEncryptProperty(String key, String value) {
		for (EncryptedConfigurationService encryptedConfigurationService : configurationServices) {
			encryptedConfigurationService.setAndEncryptProperty(key, value);
		}
	}
}
