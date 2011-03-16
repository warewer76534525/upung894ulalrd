package com.ocbcmcd.config.service;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/config-test.xml")
public class When_use_service_to_edit_config {
	
	@Autowired
	private EncryptedConfigurationService service;
	
	@Test
	public void should_encrypt_and_save_with_service() throws Exception {
		service.encryptPropertyValue("mail.username");
		
		Assert.assertEquals("triplelands2@gmail.com", service.getProperty("mail.username"));
	}
	
	@Test
	public void should_change_encrypted_property_value() throws Exception {
		service.setAndEncryptProperty("mail.password", "joesatriani");
		Assert.assertEquals("joesatriani", service.getProperty("mail.password"));
	}
}
