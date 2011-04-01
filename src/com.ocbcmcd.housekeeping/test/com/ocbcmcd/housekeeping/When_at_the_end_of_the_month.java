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

import com.ocbcmcd.housekeeping.service.IDirectoryCleaner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "/application-context.xml")
public class When_at_the_end_of_the_month {
	
	@Autowired
	private IDirectoryCleaner directoryCleaner;
	
	@Value("${failed.dir}")
	private String failedDir;
	
	@Before
	public void setUp() {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
		BasicConfigurator.configure();
	}
	
	@Test
	public void should_clean_failed_directory() throws IOException {
		directoryCleaner.clean();
		File failed = new File(failedDir);
		Assert.assertEquals(0, failed.listFiles().length);
	}
}
