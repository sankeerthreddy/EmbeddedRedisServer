package com.awesome.pro.db.redis.client;

import com.awesome.pro.pool.WrappedResource;

import java.util.Iterator;
import java.util.Set;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.exceptions.JedisConnectionException;


/**
 * Wrapper class for Redis client to make it compatible with object pooling.
 * Created by sankeerth.reddy on 05/04/15.
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

	/**
	 * Closes connection
	 */
	@Override
	public void close() {
		jedis.close();
	}

	/**
	 * Check the status
	 */
	@Override
	public boolean isClosed() {
	  try {
	    this.getResource();
	  } catch (JedisConnectionException e) {
	    return true;
	  }

	  return false;
	}


	@Override
	public Jedis getResource() {
		return jedis;
	}

	/**
	 * @param key Key to be set.
	 * @param value Corresponding value in pair.
	 */
	public final void storeData(String key, String value) {
		jedis.set(key, value);
	}

	public final String storeOrReplaceData(final String key, final String value) {
		return jedis.getSet(key, value);
	}

	/**
	 * @param key Key to be queried for.
	 * @return Corresponding value stored in Redis.
	 */
	public final String getData(String key) {
		return jedis.get(key);
	}

	/**
	 * @param key Name of unique set.
	 * @param data Data to be added.
	 */
	public final void storeOrAddUniqueData(final String key, final String... data) {
		jedis.sadd(key, data);
	}

	/**
	 * @param key Name of unique set.
	 */
	public final int getUniqueCount(final String key) {
		return Integer.parseInt(jedis.scard(key).toString());
	}

	/**
	 * @param pattern Pattern to match for.
	 * @return Set of matching keys.
	 */
	public final Set<String> queryKeys(final String pattern) {
		return jedis.keys(pattern);
	}

	/**
	 * @param pattern Pattern to match keys to be deleted.
	 */
	public final void deleteKeyPatterns(final String pattern) {
		Iterator<String> iter = jedis.keys(pattern).iterator();
		while (iter.hasNext()) {
			jedis.del(iter.next());
		}
		iter = null;
	}

	/**
	 * @param keys Specific keys to be deleted.
	 */
	public final void deleteKeys(final String... keys) {
		jedis.del(keys);
	}

	/**
	 * @param key Key to be checked.
	 * @return Whether Redis contains the specified key.
	 */
	public final boolean containsKey(final String key) {
		return jedis.exists(key);
	}

}
