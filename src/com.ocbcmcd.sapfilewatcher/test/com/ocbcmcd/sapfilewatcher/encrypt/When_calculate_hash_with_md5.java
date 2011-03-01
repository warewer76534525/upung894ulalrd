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
		System.out.println(hash);
		Assert.assertEquals("edb374934683c48c4e2a8e4bd188e6af".toUpperCase(), hash);
	}
}
