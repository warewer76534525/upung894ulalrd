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
import com.ocbcmcd.message.OcbcFileProcessedSucessfully;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_file_processed_successfully {
	private static final String FILE_NAME = "data.txt";
	
	private OcbcFileProcessedSucessfully fileProcessedEvent;
	
	@Autowired
	private IHouseKeepingService houseKeepingService;
	
	@Value("${processing.dir}")
	private String processingDirectory;
	
	@Value("${outgoing.dir}")
	private String outgoingDirectory;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		fileProcessedEvent = new OcbcFileProcessedSucessfully(FILE_NAME);
	}

	
	@Test
	public void should_move_file_from_processing_to_outgoing() throws IOException {
		File processing = new File(processingDirectory, FILE_NAME);
		File outgoing = new File(outgoingDirectory, FILE_NAME);
		
		houseKeepingService.moveSuccessFile(fileProcessedEvent);
		
		System.out.println(processing.getAbsolutePath());
		System.out.println(outgoing.getAbsolutePath());
		
		Assert.assertFalse(processing.exists());
		Assert.assertTrue(outgoing.exists());
	}
}
