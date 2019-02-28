package com.momo.lan.redis.controller;

import com.momo.lan.redis.redis.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @program: redis
 * @description:
 * @author: zhanghui2018
 * @create: 2019-02-28 10:27
 */
@RestController
public class UserController {

    @Autowired
    private RedisUtil redisUtil;
    @GetMapping("/setString")
    public String setString(String name,String value){
        redisUtil.setString(name, value);
        return "success";
    }

    @GetMapping("/setList")
    public String setList(String name){
        List<String> list = new ArrayList<>();
        list.add("123");
        list.add("456");
        redisUtil.setList(name, list);
        return "success";
    }
}
