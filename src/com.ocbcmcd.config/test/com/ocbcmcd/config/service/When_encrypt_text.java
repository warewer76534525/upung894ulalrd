package com.ocbcmcd.config.service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import org.jasypt.properties.EncryptableProperties;
import org.jasypt.util.text.BasicTextEncryptor;
import org.junit.Assert;
import org.junit.Test;

public class When_encrypt_text {

	@Test
	public void should_encrypt_plain_text() {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
		String myEncryptedText = textEncryptor.encrypt("password");
		System.out.println(myEncryptedText);

		String plainText = textEncryptor.decrypt(myEncryptedText);
		System.out.println(plainText);
	}
	
	@Test
	public void should_decrypt_chipper_text() {
		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
		

		String plainText = textEncryptor.decrypt("+1sUeXPjhn6/JtSxeU50Cw\\=\\=");
		System.out.println(plainText);
	}
	
	
	
	@Test
	public void should_get_properties_and_save() throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();

		properties.load(new FileInputStream("test/secret.properties"));
		String username = properties.getProperty("mail.username");
		Assert.assertEquals("triplelands@gmail.com", username);

		properties.setProperty("mail.username", "triplelands2@gmail.com");
		properties.store(new FileOutputStream("test/secret.properties"),
				"comment");
	}

	@Test
	public void should_encrypt_and_save() throws FileNotFoundException,
			IOException {
		Properties properties = new Properties();
		
		properties.load(new FileInputStream("test/secret.properties"));
		String username = properties.getProperty("mail.username");

		BasicTextEncryptor textEncryptor = new BasicTextEncryptor();
		textEncryptor.setPassword("1234");
		String myEncryptedText = textEncryptor.encrypt(username);
		String newValue = String.format("ENC(%s)", myEncryptedText);
		System.out.println(newValue);
		properties.setProperty("mail.username", newValue);
		properties.store(new FileOutputStream("test/secret.properties"), "mail");
		
		Properties props = new EncryptableProperties(textEncryptor);
		props.load(new FileInputStream("test/secret.properties"));
		
		System.out.println(props.getProperty("mail.username"));
		Assert.assertEquals("triplelands2@gmail.com", props.getProperty("mail.username"));
	}
}
