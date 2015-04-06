package com.awesome.pro.db.redis;

import com.awesome.pro.db.redis.client.RedisClientPool;

/**
 * Manager class for Redis server and client.
 * Created by sankeerth.reddy on 05/04/15.
 */
public class RedisManager {

	/**
	 * Initializes Redis server and client.
	 * @param configFile Configuration file for Redis.
	 */
	public static void start(final String configFile) {
		RedisServerHolder.startRedisServer(configFile);
		RedisClientPool.initializeClientPool(configFile);
	}

	/**
	 * Shuts down Redis server and client.
	 */
	public static void stop() {
		RedisClientPool.shutDownClientPool(true);
		RedisServerHolder.stopRedisServer();
	}

}
