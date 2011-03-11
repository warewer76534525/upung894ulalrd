package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;

public class When_compare_text_from_two_method {
	@Test
	public void should_compare_text_from_utils() throws Exception {
		File file = new File("110221130912_1111_03702.txt");
		FileCacheReader reader = new FileCacheReader(file);
		
		String utilsString = FileUtils.readFileToString(file);
		
		Assert.assertEquals(reader.readAllText(), utilsString);
	}
}
