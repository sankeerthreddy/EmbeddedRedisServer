package com.awesome.pro.db.redis;

import java.io.IOException;

import org.apache.log4j.Logger;
import redis.embedded.RedisServer;
import com.awesome.pro.db.redis.references.RedisConfigReferences;

/**
 * Holder class for instance of embedded Redis server.
 * Created by sankeerth.reddy on 05/04/15.
 */
class RedisServerHolder {

	/**
	 * Root logger instance.
	 */
	private final static Logger logger = Logger.getLogger(RedisServerHolder.class);

	/**
	 * Redis server instance.
	 */
	private static RedisServer REDIS_SERVER = null;

	/**
	 * @return Redis server instance.
	 */
	private static RedisServer getRedisServer(final String configFile) {
		try {
			return new RedisServer(
					RedisConfigReferences.getRedisPort(configFile)
					);
		} catch (IOException e) {
			logger.error("Unable to start Redis server.", e);
			System.exit(1);
			return null;
		}
	}

	/**
	 * Starts the Redis server.
	 * @param configFile Configuration file for Redis.
	 */
	static void startRedisServer(final String configFile) {
		try {
			REDIS_SERVER = getRedisServer(configFile);
			REDIS_SERVER.start();
			logger.info("Started Redis server.");
		} catch (IOException e) {
			logger.error("Unable to start Redis server.", e);
			System.exit(1);
		}
	}

	/**
	 * Stops the Redis server.
	 */
	static void stopRedisServer(){
			try {
				REDIS_SERVER.stop();
			} catch (InterruptedException e) {
				logger.error("Unable to stop redis server", e);
			}
		
		if (REDIS_SERVER.isActive()) {
			logger.warn("Redis server may not have shut down.");
		}
		logger.info("Stopped Redis server.");
		}
	

}
