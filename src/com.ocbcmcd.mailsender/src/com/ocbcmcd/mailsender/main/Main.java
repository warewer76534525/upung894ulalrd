package com.ocbcmcd.mailsender.main;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
	public static void main(String[] args) {
		ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(
				"mail-sender.xml");
		applicationContext.start();
	}
}
