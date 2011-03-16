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
import com.ocbcmcd.message.OcbcFileProcessFailed;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_file_processed_failed {
	private static final String FILE_NAME = "processedFailed.txt";
	
	private OcbcFileProcessFailed event;
	
	@Autowired
	private IHouseKeepingService houseKeepingService;
	
	@Value("${processing.dir}")
	private String processingDirectory;
	
	@Value("${encrypted.dir}")
	private String encryptedDirectory;
	
	@Value("${encrypted.ext}")
	private String encryptedExt;
	
	@Value("${failed.dir}")
	private String failedDirectory;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		event = new OcbcFileProcessFailed(FILE_NAME);
	}

	
	@Test
	public void should_move_file_from_incoming_to_processing() throws IOException {
		File processing = new File(processingDirectory, FILE_NAME);
		File failed = new File(failedDirectory, FILE_NAME);
		File encrypted = new File(encryptedDirectory, FILE_NAME + encryptedExt);
		
		houseKeepingService.moveOnProcessedFailed(event);
		
		System.out.println(processing.getAbsolutePath());
		System.out.println(failed.getAbsolutePath());
		
		Assert.assertFalse(processing.exists());
		Assert.assertTrue(failed.exists());
		Assert.assertTrue(!encrypted.exists());
	}
}
