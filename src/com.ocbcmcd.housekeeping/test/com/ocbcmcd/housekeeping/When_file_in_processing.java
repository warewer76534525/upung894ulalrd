package com.ocbcmcd.housekeeping;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.ocbcmcd.housekeeping.service.IHouseKeepingService;
import com.ocbcmcd.message.EncryptedFileSending;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_file_in_processing {
	private static final String FILE_NAME = "intoproc.txt";
	
	private EncryptedFileSending event;
	
	@Autowired
	private IHouseKeepingService houseKeepingService;
	
	@Value("${incoming.dir}")
	private String incomingDirectory;
	
	@Value("${processing.dir}")
	private String processingDirectory;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		event = new EncryptedFileSending(FILE_NAME);
	}

	
	@Test
	public void should_move_file_from_incoming_to_processing() throws IOException {
		File incoming = new File(incomingDirectory, FILE_NAME);
		File processing = new File(processingDirectory, FILE_NAME);
		
		houseKeepingService.moveOnProcessingFile(event);
		
		System.out.println(incoming.getAbsolutePath());
		System.out.println(processing.getAbsolutePath());
		
		Assert.assertFalse(incoming.exists());
		Assert.assertTrue(processing.exists());
	}
}
