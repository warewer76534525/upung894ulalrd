package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;

import org.apache.commons.io.FileUtils;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class When_generate_file_with_header {
	
	@Test
	public void Should_get_the_same_header_with_sample() {
		FileCacheReader fileCacheReader = new FileCacheReader(new File("110304100746_0001_00002.txt"));
		Assert.assertEquals(1, fileCacheReader.getLinesCount());
		
		Header header = new Header(new File("110304100746_0001_00002.txt"));
		Assert.assertEquals("c013b0a1133fb22b03fe9e65bd8dfdb7;1;20110304", header.toString());
		System.out.println(header.toString());
	}
	
	@Test
	public void Should_generate_encrypted_file_from_sample() throws Exception {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("sap-file-watcher.xml");
		EncryptedFileTransformer transformer = context.getBean(EncryptedFileTransformer.class);
		transformer.create(new File("D:/download/TestEncryption/TestEncryption/Plain Text/110304100746_0001_00002.txt"));
	}
	
	@Test
	public void Should_test_file_contents_equals() throws IOException {
		File encryptedFromOcbc = new File("D:/github/file-sender/resource/TestEncryption/Encyrpted/110304100746_0001_00002.txt.gpg");
		File encryptedFromLocal = new File("C:/erpfile/encrypted/110304100746_0001_00002.txt.gpg");
		System.out.println(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
		Assert.assertTrue(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
	}
	
	@Test
	public void Should_test_file_contents_equals_console() throws IOException {
		File encryptedFromOcbc = new File("D:/experiment/test-enkripsi-pgp/mcdonald/110304100746_0001_00002.txt.gpg");
		File encryptedFromLocal = new File("D:/experiment/test-enkripsi-pgp/110304100746_0001_00002.txt.gpg");
		System.out.println(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
		Assert.assertTrue(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
	}
	
	@Test
	public void Should_test_key_contents_equals_console() throws IOException {
		File encryptedFromOcbc = new File("D:/download/McDonald.asc");
		File encryptedFromLocal = new File("D:/github/file-sender/resource/TestEncryption/Public Key/McDonald.asc");
		System.out.println(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
		Assert.assertTrue(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
	}
	
	@Test
	public void Should_test_file_contents_equals_for_header() throws IOException {
		File encryptedFromOcbc = new File("D:/github/file-sender/resource/TestEncryption/Encyrpted/110304100746_0001_00002.txt");
		File encryptedFromLocal = new File("C:/erpfile/encrypted/110304100746_0001_00002.txt");
		Assert.assertTrue(FileUtils.contentEquals(encryptedFromOcbc, encryptedFromLocal));
	}
	
	@Test
	public void Should_test_file_contents_string() throws IOException {
		File encryptedFromOcbc = new File("D:/github/file-sender/resource/TestEncryption/Encyrpted/110304100746_0001_00002.txt");
		File encryptedFromLocal = new File("C:/erpfile/encrypted/110304100746_0001_00002.txt");
		System.out.println(FileUtils.readFileToString(encryptedFromOcbc));
		System.out.println(FileUtils.readFileToString(encryptedFromLocal));
		Assert.assertEquals(FileUtils.readFileToString(encryptedFromOcbc), FileUtils.readFileToString(encryptedFromLocal));
	}
	
	@Test
	public void Should_test_file_bytes_string() throws IOException {
		File encryptedFromOcbc = new File("D:/github/file-sender/resource/TestEncryption/Encyrpted/110304100746_0001_00002.txt");
		File encryptedFromLocal = new File("C:/erpfile/encrypted/110304100746_0001_00002.txt");
		
		char[] ocbc = FileUtils.readFileToString(encryptedFromOcbc).toCharArray();
		char[] local = FileUtils.readFileToString(encryptedFromLocal).toCharArray();
		for (int i = 0; i < ocbc.length; i++) {
			if (ocbc[i] != local[i]) {
				System.out.println("Not equals");
				System.out.write(ocbc[i]);
				System.out.write(' ');
				System.out.write(local[i]);
				System.out.println();
			} else {
				System.out.println(ocbc[i] + "  " + local[i]);
			}
		}
	}
	
	@Test
	public void Should_show_line_separator() {
		System.out.println(System.getProperty("line.separator").toCharArray().length);
	}
}
