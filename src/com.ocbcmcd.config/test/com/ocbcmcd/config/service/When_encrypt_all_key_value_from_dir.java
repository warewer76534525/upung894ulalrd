package com.ocbcmcd.config.service;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class When_encrypt_all_key_value_from_dir {
	
	@Test
	public void Should_encrypt_all_values() {
//		String parentLocation = "D:/github/file-sender/installer/bin/";
//		String[] servicesName = { 
//				"sapfilewatcher-service", 
//				"mailsender-service", 
//				"confirmwatcher-service", 
//				"ftpfilesender-service", 
//				"housekeeping-service"};
//		
//		String configName = "application.properties";
//		String[] encryptedKeyList = {"ftp.host", "ftp.username", "ftp.password"};

		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-tools.xml");
		EncryptorService encryptorService =  context.getBean(EncryptorService.class);//new EncryptorService(parentLocation, servicesName, configName, encryptedKeyList);
		encryptorService.encryptAllValues();
	}
	
	@Test
	public void Should_change_property_values_based() {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-tools.xml");
		PropertyService propertyService =  context.getBean(PropertyService.class);
		
		propertyService.changeAllConfigValues();
	}
}
