package com.example.redis.config;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.data.redis.core.*;

import java.util.Set;



@SpringBootTest
class RedisConfigTest {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void 테스트(){
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        String key = "springTest";
        valueOperations.set(key,"hello");
        String value = valueOperations.get(key);
        System.out.println(value  + "===============");

    }

    @Test
    void Set테스트(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key = "dress";
        Set<String> members = set.members(key);
        Long size = set.size(key);
        System.out.println(members);
        System.out.println(size);
        System.out.println(members.contains("prd002"));
        System.out.println(members.contains("prd0021"));
    }

    @Test
    void 테스트작성(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key = "fruit";
        Set<String> members = set.members(key);
        Long size = set.size(key);

        System.out.println(size + "===============");
        System.out.println(members + "===============");


    }

    @Test
    void examSet(){
        SetOperations<String, String> set = redisTemplate.opsForSet();
        String key  ="fruit";
        set.add(key,"banana1");
        set.add(key,"banana2");
        set.add(key,"banana3");
        set.add(key,"banana4");
        set.add(key,"banana5");

        Set<String> members = set.members(key);
        System.out.println(members.contains("banana1"));

    }


}