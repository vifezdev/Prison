package gg.convict.prison.util.redis;

import redis.clients.jedis.Jedis;

/**
 * @author Emilxyz (langgezockt@gmail.com)
 * 12.02.2020 / 22:11
 * libraries / cc.invictusgames.libraries.redis
 */

public interface RedisCommand<T> {

    public T execute(Jedis redis);

}
