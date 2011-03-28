package com.ocbcmcd.config.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.ocbcmcd.config.service.EncryptorService;
import com.ocbcmcd.config.service.PropertyService;

public class Main {
	public static void main(String[] args) {
		
		if (args.length == 0) {
			System.out.println("Usage: encrypt/changevalue");
			return;
		}
		
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("config-tools.xml");
		
		if (args[0].equals("encrypt")) {
			EncryptorService encryptorService =  context.getBean(EncryptorService.class);//new EncryptorService(parentLocation, servicesName, configName, encryptedKeyList);
			encryptorService.encryptAllValues();
		} else if (args[0].equals("changevalue")) {
			PropertyService propertyService =  context.getBean(PropertyService.class);
			propertyService.changeAllConfigValues();
		} else {
			System.out.println("Usage: encrypt/changevalue");
		}
		
	}
}
