package com.lanzong;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    
    @Autowired
    private RedisTemplate redisTemplate;
    
    @Test
    public void test(){
        redisTemplate.opsForValue().set("name","lanzong2");
        System.out.println(redisTemplate.opsForValue().get("name"));
        Map<String,Object> map = new HashMap<>();
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setId(1);
            user.setName(String.format("姓名%d",i));
            user.setAge(i+10);
            map.put(String.valueOf(i),user);
        }
        redisTemplate.opsForHash().putAll("users",map);
        //hash操作
        BoundHashOperations hashOps = redisTemplate.boundHashOps("users");
        Map map1 = hashOps.entries();
        map1.forEach((key,value)->{
            User user = (User) value;
            System.out.println(user.toString());
        });
    }

    static class User implements Serializable{
        private int id;
        private String name;
        private int age;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        @Override
        public String toString() {
            return "User{" +
                    "id=" + id +
                    ", name='" + name + '\'' +
                    ", age=" + age +
                    '}';
        }
    }
}
