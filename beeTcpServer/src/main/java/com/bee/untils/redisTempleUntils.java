package com.bee.untils;

import redis.clients.jedis.Jedis;

/**
 * @author yh
 * @date 2022/9/30 下午11:25
 */
public class redisTempleUntils{
    private static Jedis jedis;
    private static final String addr="127.0.0.1";
    private static final int port=6379;

    static {
        jedis=new Jedis(addr,port);
    }
    public static String Get(String key){
        return jedis.get(key);
    }
}
