package com.ocbcmcd.config.service;

import org.jasypt.util.text.BasicTextEncryptor;

public class EncryptedConfigurationService {

	private BasicTextEncryptor textEncryptor;
	private PersistenceProperties persistenceProperties;
	
	public EncryptedConfigurationService(String location) {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
		
		persistenceProperties = new PersistenceProperties(location);
	}
	
	public void encryptPropertyValue(String key) throws Exception {		
		if (isAlreadyEncrypted(key)) return;
		
		String value = persistenceProperties.getProperty(key);
		
		persistenceProperties.setProperty(key, encryptConfigValue(value));
	}

	private boolean isAlreadyEncrypted(String key) {
		String value = persistenceProperties.getPlainProperty(key);
		
		return value.startsWith("ENC(");
	}

	private String encryptConfigValue(String value) {
		String myEncryptedText = textEncryptor.encrypt(value);
		return String.format("ENC(%s)", myEncryptedText);
	}

	public String getProperty(String key) {
		return persistenceProperties.getProperty(key);
	}

	public void setAndEncryptProperty(String key, String value) {
		persistenceProperties.setProperty(key, encryptConfigValue(value));
	}
}
