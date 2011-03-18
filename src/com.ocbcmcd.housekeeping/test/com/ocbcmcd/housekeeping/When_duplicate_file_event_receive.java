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
import com.ocbcmcd.message.SapFileDuplicated;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_duplicate_file_event_receive {
	private static final String FILE_NAME = "duplicate.txt";
	
	private SapFileDuplicated event;
	
	@Autowired
	private IHouseKeepingService houseKeepingService;
	
	@Value("${incoming.dir}")
	private String incomingDirectory;
	
	@Value("${failed.dir}")
	private String failedDirectory;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
				
		event = new SapFileDuplicated(FILE_NAME);
	}

	
	@Test
	public void should_move_file_from_incoming_to_processing() throws IOException {
		File incoming = new File(incomingDirectory, FILE_NAME);
		File failed = new File(failedDirectory, FILE_NAME);
		
		houseKeepingService.moveOnDuplicatedFile(event);
		
		System.out.println(incoming.getAbsolutePath());
		System.out.println(failed.getAbsolutePath());
		
		Assert.assertFalse(incoming.exists());
		Assert.assertTrue(failed.exists());
	}
}
