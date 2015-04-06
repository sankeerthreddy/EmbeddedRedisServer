package com.awesome.pro.db.redis.client;

import redis.clients.jedis.Jedis;

import com.awesome.pro.db.redis.references.RedisConfigReferences;
import com.awesome.pro.pool.AcquireResource;

/**
 * Specifies how to acquire a new Redis client connection.
 * Created by sankeerth.reddy on 05/04/15.
 */
public class AcquireRedisConnection implements AcquireResource<WrappedRedisClient> {

	// Constructor.
	public AcquireRedisConnection() { }

	@Override
	public WrappedRedisClient acquireResource() {
		return new WrappedRedisClient(new Jedis(
				RedisConfigReferences.REDIS_HOST,
				RedisConfigReferences.getRedisPort(null)));
	}

}
