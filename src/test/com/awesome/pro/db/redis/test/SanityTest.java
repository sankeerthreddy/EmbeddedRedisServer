package com.awesome.pro.db.redis.test;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.awesome.pro.db.redis.RedisManager;
import com.awesome.pro.db.redis.client.RedisClientPool;
import com.awesome.pro.db.redis.client.WrappedRedisClient;

/**
 * Basic transaction test for sanity.
 * @author siddharth.s
 */
public class SanityTest {

	/**
	 * Redis client.
	 */
	private WrappedRedisClient client;

	@BeforeClass
	public void setup() {
		RedisManager.start("redis.properties");
		client = RedisClientPool.getRedisClient();
	}

	@AfterClass
	public void tearDown() {
		RedisClientPool.returnRedisClient(client);
		RedisManager.stop();
	}

	@Test(groups = { "sanity" })
	public void test1() {
		client.getResource().set("key1", "val1");
		Assert.assertEquals(client.getResource().get("key1"), "val1");
	}

}
