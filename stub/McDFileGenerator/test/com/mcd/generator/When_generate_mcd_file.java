package com.mcd.generator;


import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("/file-generator.xml")
public class When_generate_mcd_file {
	
	@Autowired private FileGenerator _fileGenerator;
	
	@Before
	public void setUp() throws Exception {
		Logger.getRootLogger().setLevel(Level.INFO);
		Logger.getLogger("org.springframework").setLevel(Level.WARN);
	}
	
	@Test
	public void should_write_file_to_incoming_directory() throws IOException {
		String filePath = _fileGenerator.generate();
		File generatedFile = new File(filePath);
		Assert.assertTrue(generatedFile.exists());
	}
}
