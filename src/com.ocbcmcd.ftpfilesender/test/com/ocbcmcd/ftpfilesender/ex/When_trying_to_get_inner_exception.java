package com.ocbcmcd.ftpfilesender.ex;

import java.io.File;

import org.apache.commons.lang.exception.ExceptionUtils;
import org.junit.Test;

public class When_trying_to_get_inner_exception {
	
	@Test
	public void should_get_inner_exception_message() {
		
		try {
			File file = new File("//");
			file.createNewFile();
		} catch (Exception e) {
			System.out.println(ExceptionUtils.getCause(e));
		}
	}
}
