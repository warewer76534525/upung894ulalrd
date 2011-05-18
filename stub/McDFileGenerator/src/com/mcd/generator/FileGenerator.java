package com.mcd.generator;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class FileGenerator {
	@Value("${incoming.dir}")
	private String incomingDir;
	
	@Value("${sample.file}")
	private String sampleFile;
	
	private DateFormat format;
	private static final String dateFormat = "ddmmyy_hhmmss";
	
	public FileGenerator() {
		format = new SimpleDateFormat(dateFormat);
	}
	
	public String generate() throws IOException {
		String generatedFileName = "110221130912_" + format.format(new Date()) + ".txt";
		File desFile = new File(incomingDir, generatedFileName);
		File srcFile = new File(sampleFile);
	
		FileUtils.copyFile(srcFile, desFile);
		
		return desFile.getAbsolutePath();
	}
}

