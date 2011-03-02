package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.File;

import org.apache.commons.exec.CommandLine;
import org.apache.commons.exec.DefaultExecutor;
import org.junit.Assert;
import org.junit.Test;



public class When_execute_gnupg {
	@Test
	public void Should_encrypt_file() throws Exception {
		String line = "gpg -r 544F85D3 -e " + "D:\\ocbcsite\\movie-hunt.txt";
		CommandLine cmdLine = CommandLine.parse(line);
		DefaultExecutor executor = new DefaultExecutor();
		int exitValue = executor.execute(cmdLine);
		System.out.println(exitValue);
	}
	
	@Test
	public void Should_encrypt_file_with_parameter() throws Exception {
		GPGWindowsEncryptor gpgWindowsEncryptor = new GPGWindowsEncryptor();
		File originalFile = new File("D:\\ocbcsite\\movie-hunt.txt");
		gpgWindowsEncryptor.encrypt("McDonald", originalFile);
	}
	
	@Test
	public void Should_get_new_encrypted_file_name() {
		File originalFile = new File("D:\\ocbcsite\\movie-hunt.txt");
		System.out.println(originalFile.getName());
		System.out.println(originalFile.getParent());
	}
	
	@Test
	public void Should_remove_file() {
		File file = new File("D:\\ocbcsite\\movie-hunt.txt");
		file.delete();
		Assert.assertFalse(file.exists());
	}
}
