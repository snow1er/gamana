package com.jiayu.gamana.util;

import java.util.UUID;

import org.junit.Assert;
import org.junit.Test;

/**
 * Test method for StringUtils
 * @author Neo.Li
 */
public class StringUtilsTest {

	@Test
	public void testStringIsUUID() {
		String uuid = UUID.randomUUID().toString();
		Assert.assertTrue(StringUtils.isUUID(uuid));;
	}
	
	@Test
	public void testRandomString() {
		String uuid = StringUtils.getRandomString(32);
		System.out.println(uuid);
		Assert.assertNotNull(uuid);
	}
}
