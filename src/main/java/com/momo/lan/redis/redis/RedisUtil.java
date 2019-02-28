package com.momo.lan.redis.redis;

import io.netty.util.internal.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @program: redis
 * @description:
 * @author: zhanghui2018
 * @create: 2019-02-28 11:06
 */
@Service
public class RedisUtil {

    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /*
        java重载
     */
    public void setString(String key, String string){
        this.setObject(key, string, null);
    }
    public void setString(String key, String string, Long time){
        this.setObject(key, string, time);
    }
    public void setList(String key, List<String> list){
        this.setObject(key, list, null);
    }
    public void setList(String key, List<String> list, Long time){
        this.setObject(key, list, time);
    }

    public void setObject(String key, Object value, Long time){
        // redis有5中类型 string, list , set , zset , hash
        // 有效期
        if(StringUtil.isNullOrEmpty(key) || value == null){
            return;
        }
        // 判断类型 存放String类型
        if (value instanceof String){
            String strValue = (String) value;
            stringRedisTemplate.opsForValue().set(key,strValue);
            if (time!=null){
                stringRedisTemplate.opsForValue().set(key,strValue, time, TimeUnit.SECONDS);
            }
            return;
        }
        // 判断类型 存放List类型
        if (value instanceof List){
            List<String> list = (List<String>) value;
            if (time!=null){
                for (String string : list) {
                    stringRedisTemplate.opsForList().rightPopAndLeftPush(key,string,time,TimeUnit.SECONDS);
                }
            }else {
                for (String string : list) {
                    stringRedisTemplate.opsForList().leftPush(key,string);
                }
            }

            return;
        }
        // 判断类型 存放set类型
        if (value instanceof Set){
            Set<String> set = (Set<String>) value;
            for (String string : set) {
                stringRedisTemplate.opsForSet().intersect(key,string);
            }
            return;
        }


    }
}
