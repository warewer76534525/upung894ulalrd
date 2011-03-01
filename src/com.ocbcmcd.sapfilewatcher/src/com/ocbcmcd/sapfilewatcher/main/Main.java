package com.ocbcmcd.sapfilewatcher.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"sap-file-watcher.xml");
		applicationContext.start();
	}
}
