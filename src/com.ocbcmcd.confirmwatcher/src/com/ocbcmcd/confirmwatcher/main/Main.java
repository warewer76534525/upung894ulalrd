package com.ocbcmcd.confirmwatcher.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"ftp-confirm-watcher.xml");
		applicationContext.start();
	}
}
