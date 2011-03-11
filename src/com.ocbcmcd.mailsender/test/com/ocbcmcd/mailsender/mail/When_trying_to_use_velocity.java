package com.ocbcmcd.mailsender.mail;

import java.util.HashMap;
import java.util.Map;

import org.apache.velocity.app.VelocityEngine;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/mail-sender-test.xml")
public class When_trying_to_use_velocity {
	
	@Autowired
	private VelocityEngine velocityEngine;
	
	@Test
	public void should_return_string_filled_with_paraam() {
		 Map model = new HashMap();
         model.put("userName", "Welly Tambunan");
         model.put("emailAddress", "if05041@gmail.com");
         String text = VelocityEngineUtils.mergeTemplateIntoString(
            velocityEngine, "error-notification.vm", model);
         System.out.println(text);
	}
}
