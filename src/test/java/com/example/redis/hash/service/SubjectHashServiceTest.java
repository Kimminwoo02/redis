package com.example.redis.hash.service;

import com.example.redis.hash.dto.Subject;
import com.example.redis.hash.repository.SubjectRedisRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
class SubjectHashServiceTest {
    @Autowired
    private SubjectRedisRepository repository;

    @Autowired
    private RedisTemplate<String,String> redisTemplate;

    @Test
    void save(){
        Subject subject = new Subject("subject001", "java",25000,"info");

        Subject result = repository.save(subject);

        System.out.println(subject);
        System.out.println(result);
        System.out.println(result.getId().equals(subject.getId()));
    }

    @Test
    void find(){
        Subject subject = new Subject("subject001", "java",25000,"info");

        Subject subject001 = repository.findById("subject001").get();

        System.out.println(subject001);

    }

}