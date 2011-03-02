package com.ocbcmcd.ftpfilesender.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"ftp-file-sender.xml");
		applicationContext.start();
	}
}
