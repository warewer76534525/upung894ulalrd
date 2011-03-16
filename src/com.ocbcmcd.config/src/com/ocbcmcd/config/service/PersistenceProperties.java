package com.ocbcmcd.config.service;


import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.Properties;

import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;

public class PersistenceProperties {
	private String fileLocation;
	private BasicTextEncryptor textEncryptor;
	
	public PersistenceProperties(String location) {
		this.fileLocation = location;
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
	}

	public String getProperty(String key) {
		Properties properties = new EncryptableProperties(textEncryptor);
		try {
			properties.load(new FileInputStream(fileLocation));
			return properties.getProperty(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public void setProperty(String key, String newValue) {
		Properties properties = new EncryptableProperties(textEncryptor);
		
		try {
			
			properties.load(new FileInputStream(fileLocation));
			properties.setProperty(key, newValue);
			properties.store(new FileOutputStream(fileLocation), "mail");
			
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		
	}

	public String getPlainProperty(String key) {
		Properties properties = new Properties();
		try {
			properties.load(new FileInputStream(fileLocation));
			return properties.getProperty(key);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
