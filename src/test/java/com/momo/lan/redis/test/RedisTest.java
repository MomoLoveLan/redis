package com.momo.lan.redis.test;

import redis.clients.jedis.Jedis;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @program: redis
 * @description:
 * @author: zhanghui2018
 * @create: 2019-02-28 10:28
 */
public class RedisTest {
    private static Jedis jedis = null;
    public static void main(String[] args) {
        jedis = new Jedis("127.0.0.1",6379);//192.168.10.168
        jedis.auth("123456");
        //setJedis();
        //System.out.println(getJedis("name"));
        //setMap();
        //System.out.println(getMap());
        //setList();
        //zSet();
        System.out.println("success");
    }
    public static void setJedis(){
        jedis.set("age","12");
    }
    public static String getJedis(String key){
        String value = jedis.get(key);
        return value;
    }
    public static void setMap(){
        Map<String,String> map = new HashMap<>();
        map.put("momo", "15000");
        jedis.hmset("jiuye", map);

    }
    public static Map getMap(){
        Map map = jedis.hgetAll("jiuye");
        return map;
    }
    public static void setList(){
        jedis.sadd("a","200");
        jedis.sadd("b","400");
    }
    public static void zSet(){
        //jedis.zadd("1",1,"A");
        jedis.zadd("1",3,"B");
        jedis.zadd("1",2,"C");
    }
}
