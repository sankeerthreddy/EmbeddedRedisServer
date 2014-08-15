EmbeddedRedisServer
===================

Embdedded Redis server and pooled redis clients.
All data that may have been stored is deleted at startup and shutdown.

Sample code for initialization:

````java
RedisManager.start("redis.properties");
WrappedRedisClient client = RedisClientPool.getRedisClient();

client.getResource().set("key1", "val1");
client.getResource().get("key1");

RedisClientPool.returnRedisClient(client);
RedisManager.stop();
````
