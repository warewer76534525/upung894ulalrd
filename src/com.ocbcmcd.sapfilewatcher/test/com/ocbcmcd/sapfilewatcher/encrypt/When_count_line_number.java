package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

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
		Assert.assertEquals("b4678cf166605de90ab587a636c8cd28;153;20110303",
				header.toString());
	}

	@Test
	public void Should_return_header_for_file_x() {
		StringBuffer sourceSb = new StringBuffer();
		StringBuffer desSb = new StringBuffer();
		try {
			BufferedReader sourceReader = new BufferedReader(new FileReader(
					"C:/erpfile/encrypted/110221130912_1111_03702.txtc.txt"));
			String text;
			while ((text = sourceReader.readLine()) != null) {
				sourceSb.append(text);
			}

			BufferedReader desReader = new BufferedReader(new FileReader(
					"C:/erpfile/encrypted/110221130912_1111_03702.txt"));
			
			while ((text = desReader.readLine()) != null) {
				desSb.append(text);
			}
			
			System.out.println(sourceSb.toString());
			System.out.println(desSb.toString());
			Assert.assertEquals(sourceSb.toString(), desSb.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void Should_return_line_count_from_sample_file() {
		FileCacheReader fileCacheReader = new FileCacheReader(new File(
				"110221130912_1111_03702.txt"));
		Assert.assertEquals(6, fileCacheReader.getLinesCount());

		Header header = new Header(new File("110221130912_1111_03702.txt"));
		System.out.println(header.toString());
	}

	@Test
	public void Should_cache_file_content() throws Exception {
		FileCacheReader fileCacheReader = new FileCacheReader(new File(
				"INPUT.txt"));
		fileCacheReader.readAllText();
		fileCacheReader.readAllText();
		fileCacheReader.readAllText();

	}
}
