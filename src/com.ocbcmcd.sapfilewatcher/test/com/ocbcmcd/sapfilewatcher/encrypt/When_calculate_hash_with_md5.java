package com.ocbcmcd.sapfilewatcher.encrypt;

import java.io.FileInputStream;

import junit.framework.Assert;

import org.apache.commons.codec.digest.DigestUtils;
import org.junit.Test;

public class When_calculate_hash_with_md5 {
	
	@Test
	public void should_return_string_hash() throws Exception {
		String hash = DigestUtils.md5Hex(new FileInputStream("INPUT.txt")).toUpperCase();
		Assert.assertNotNull(hash);
		System.out.println(hash.toLowerCase());
		Assert.assertEquals("B4678CF166605DE90AB587A636C8CD28".toUpperCase(), hash);
	}
}
