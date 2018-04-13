package com.yu.test;

import redis.clients.jedis.Jedis;

/**
 * @author Thanos Yu
 * @date 2017/11/27
 */
public class RedisTest {

    public static void main(String[] args) {
        Jedis jedis = new Jedis("127.0.0.1",6379);
//        jedis.set("kaka","AC");
//        jedis.expire("kaka",20);
        System.out.println(jedis.get("kaka"));
    }
}
