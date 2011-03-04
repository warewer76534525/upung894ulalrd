package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import junit.framework.Assert;

import org.junit.Test;

public class When_count_line_number {
	@Test
	public void should_return_all_line_number() throws Exception {
		FileInformation fileInfo = new FileInformation(new File("INPUT.txt"));
		
		String content = fileInfo.getContent();
		System.out.println(content.split("\\n").length);
	}
	
	@Test
	public void Should_return_date_string() {
		DateString dateString = new DateString();
		Assert.assertEquals("20110303", dateString.toString());
	}
	
	@Test
	public void Should_return_header_for_file() {
		Header header = new Header(new File("INPUT.txt"));
		Assert.assertEquals("b4678cf166605de90ab587a636c8cd28;153;20110303", header.toString());
	}
	
	@Test
	public void Should_return_line_count_from_sample_file() {
		FileCacheReader fileCacheReader = new FileCacheReader(new File("110221130912_1111_03702.txt"));
		Assert.assertEquals(6, fileCacheReader.getLinesCount());
		
		Header header = new Header(new File("110221130912_1111_03702.txt"));
		System.out.println(header.toString());
	}
	
	@Test
	public void Should_cache_file_content() throws Exception {
		FileCacheReader fileCacheReader = new FileCacheReader(new File("INPUT.txt"));
		fileCacheReader.readAllText();
		fileCacheReader.readAllText();
		fileCacheReader.readAllText();
	}
}
