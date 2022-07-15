//package com.erhui;
//
//import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.test.context.junit4.SpringRunner;
//import org.springframework.transaction.annotation.Transactional;
//import redis.clients.jedis.Jedis;
//
///**
// * author:erhui
// * version:1.0
// **/
//
//@SpringBootTest
//@RunWith(SpringRunner.class)
//public class uploadFileTest {
//
//    @Test
//    public void test1(){
//        String filename = "test.jpg";
//        String suffix = filename.substring(filename.lastIndexOf("."));
//        System.out.println(suffix);
//    }
//
//}
//
//class JedisTest{
//
//    @Test
//    public void testRedis(){
//        Jedis localhost = new Jedis("localhost", 6379);
//        localhost.set("username","xiaoming");
//        String username = localhost.get("username");
//        System.out.println(username);
////        localhost.del("username");
//        localhost.hset("myhash","addr","bj");
//        localhost.close();
//    }
//
//}
//
//class RedisTest{
//
//    @Autowired
//    private RedisTemplate redisTemplate;
//
//    @Test
//    public void testString(){
//
//        redisTemplate.opsForValue().set("city","beijing");
//
//    }
//}
