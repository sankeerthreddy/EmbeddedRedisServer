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

Sample configuration file: ![redis.properties](http://github.opslab.sv2.tellme.com/OnlineAutomation/EmbeddedRedisServer/raw/master/conf/redis.properties)
It doesn't have to be named redis.properties.
As long as the parameter names are same, any file could be specified during initialization.
