package com.mcd.generator;

import java.io.IOException;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileGeneratorThread {
	
	protected final static Log log = LogFactory.getLog(FileGeneratorThread.class);
	
	@Autowired private FileGenerator _fileGenerator;
	
	@PostConstruct
	public void startUp() {
		
		Thread chatManagerThread = new Thread() {
			@Override
			public void run() {
				while (true) {
					try {
						_fileGenerator.generate();
						Thread.sleep(5 * 1000);
					} catch (Exception e) {
						log.error(e.getMessage(), e);
					}
				}
			}
		};
		
		chatManagerThread.setName("McD File Generator");
		chatManagerThread.setDaemon(false);
		chatManagerThread.start();
		
	}
}
