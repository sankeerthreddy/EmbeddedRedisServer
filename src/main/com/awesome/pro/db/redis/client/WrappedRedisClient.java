package com.awesome.pro.db.redis.client;

import redis.clients.jedis.Jedis;

import com.awesome.pro.pool.WrappedResource;

/**
 * Wrapper class for Redis client to make it compatible with object pooling.
 * @author siddharth.s
 */
public class WrappedRedisClient implements WrappedResource<Jedis> {

	/**
	 * Wrapped Redis client.
	 */
	private final Jedis jedis;

	/**
	 * @param redisClient Redis client instance to be wrapped.
	 */
	public WrappedRedisClient(final Jedis redisClient) {
		jedis = redisClient;
	}

	/* (non-Javadoc)
	 * @see com.awesome.pro.pool.WrappedResource#close()
	 */
	@Override
	public void close() {
		jedis.close();
	}

	/* (non-Javadoc)
	 * @see com.awesome.pro.pool.WrappedResource#isClosed()
	 */
	@Override
	public boolean isClosed() {
		//FIXME: Handle with a try-catch block.
		return false;
	}

	/* (non-Javadoc)
	 * @see com.awesome.pro.pool.WrappedResource#getResource()
	 */
	@Override
	public Jedis getResource() {
		return jedis;
	}

}
