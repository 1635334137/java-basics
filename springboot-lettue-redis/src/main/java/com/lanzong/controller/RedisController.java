package com.lanzong.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 仅用于测试
 * 应在Servcie层使用redis
 */
@RestController
@RequestMapping("/redis")
public class RedisController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RedisController.class);

    @Autowired
    private RedisTemplate redisTemplate;

    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    @RequestMapping("/stringAndHash")
    public Map<String,Object> testStringAndHash(){
        redisTemplate.opsForValue().set("key1","value1");
        //这里使用JDK的序列化器，Redis保存时不是整数，不能运算
        redisTemplate.opsForValue().set("int_key","1");

        //使用运算 increment +1操作
        stringRedisTemplate.opsForValue().set("int","1");
        stringRedisTemplate.opsForValue().increment("int",1);

        Map<String,Object> hash = new HashMap<>();
        hash.put("field1","value1");
        hash.put("field2","vaule2");
        stringRedisTemplate.opsForHash().putAll("hash2",hash);
        stringRedisTemplate.opsForHash().put("hash3","field3","value3");

        BoundHashOperations hashOps = stringRedisTemplate.boundHashOps("hash2");
        hashOps.delete("field2","field1");//删除元素
        hashOps.put("field4","value4");//不会存到redis中，只是添加到集合中
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("/list")
    public Map<String,Object> testList(){
        //链表从左到右的顺序为：v5 v4 v2
        stringRedisTemplate.opsForList().leftPushAll("list1","v2","v4","v5");
        //链表从左到右的顺序为：v9 v10 v8
        stringRedisTemplate.opsForList().rightPushAll("list2","v9","v10","v8");

        //绑定list2操作链表
        BoundListOperations listOps = stringRedisTemplate.boundListOps("list2");
        Object result = listOps.rightPop();//从右边弹出一个成员
        LOGGER.info("list2最右边的元素为："+result.toString());

        Object result2 = listOps.index(1);//获取定位元素 下标从0开始
        LOGGER.info("list2下标为1的元素为："+result2.toString());

        listOps.leftPush("v0");//从左边插入链表

        Long size =  listOps.size();//求链表长
        LOGGER.info("list2的长度为："+size);

        List element = listOps.range(0,size-1);//求链表区间成员
        LOGGER.info("list2区间内的元素为："+element.toString());


        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    @RequestMapping("/set")
    public Map<String,Object> testSet() {
        //重复的元素不会被插入
        stringRedisTemplate.opsForSet().add("set1","v1","v1","v2");

        BoundSetOperations setOps = stringRedisTemplate.boundSetOps("set1");
        setOps.add("v11","v12");
        setOps.remove("v1");
        Set set = setOps.members();//返回所有元素
        LOGGER.info("所有元素："+set.toString());

        //setOps.intersect("xxx");//求交集
        //setOps.intersectAndStore("xxx","new_xxx");//求交集并用新集合保存

        //setOps.diff("");//差集
        //setOps.union("");//并集

        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }

    /**
     * 不建议使用redis事务，因为redis对于事务的支持并不是关系型数据库那样满足ACID。
     * Redis事务只能保证ACID中的隔离性和一致性，无法保证原子性和持久性。
     * 可使用Spring提供的注解式事务
     * @return
     */
    @RequestMapping("/multi")
    public Map<String,Object> testMulti(){
        List<Object> list = stringRedisTemplate.execute(new SessionCallback<List<Object>>() {
            @Override
            public List<Object> execute(RedisOperations operations) throws DataAccessException {
                operations.multi();
                operations.opsForSet().add("data1","value1");
                return operations.exec();
            }
        });
        Map<String,Object> map = new HashMap<>();
        map.put("success",true);
        return map;
    }
}
