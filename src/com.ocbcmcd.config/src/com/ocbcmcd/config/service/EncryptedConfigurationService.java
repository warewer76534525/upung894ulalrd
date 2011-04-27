package com.ocbcmcd.config.service;

import java.util.List;

import org.jasypt.util.text.BasicTextEncryptor;
import org.springframework.util.StringUtils;

public class EncryptedConfigurationService {

	private BasicTextEncryptor textEncryptor;
	private PersistenceProperties persistenceProperties;
	private List<String> encryptedKeyLists;
	
	public EncryptedConfigurationService(String location, List<String> encryptedKeyLists) {
		textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
		
		persistenceProperties = new PersistenceProperties(location);
		
		this.encryptedKeyLists = encryptedKeyLists;
	}
	
	public void encryptPropertyValue(String key) throws Exception {
		if (!isPropertyExists(key)) return;
		
		if (isAlreadyEncrypted(key)) return;
		
		String value = persistenceProperties.getProperty(key);
		
		persistenceProperties.setProperty(key, encryptConfigValue(value));
	}

	public boolean isPropertyExists(String key) {
		String value = persistenceProperties.getPlainProperty(key);
		
		return StringUtils.hasText(value);
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
	
	public void setProperty(String key, String value) {
		persistenceProperties.setProperty(key, value);
	}

	public void encryptPropertyValues(String... keys) {
		for (String key : keys) {
			try {
				encryptPropertyValue(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public void encryptSecretPropertyValues() {
		for (String key : encryptedKeyLists) {
			try {
				encryptPropertyValue(key);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
}
