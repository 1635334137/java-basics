package com.lanzong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class RedisServcieImpl {

    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 伪代码写法
     * @return
     */
    public Object find(){
        ValueOperations<String,Object> operations = redisTemplate.opsForValue();

        //缓存存在
        Boolean hasKey = redisTemplate.hasKey("key");
        if(hasKey){
            Object object = operations.get("key");
            return object;
        }

        //不存在，插入缓存 (设置缓存时间)
        operations.set("key","data",10, TimeUnit.SECONDS);
        return "data";
    }

    public Object update(){
        //更新语句

        //缓存存在，删除缓存
        Boolean hasKey = redisTemplate.hasKey("key");
        if (hasKey) {
            redisTemplate.delete("key");
        }
        return "data";
    }
}
