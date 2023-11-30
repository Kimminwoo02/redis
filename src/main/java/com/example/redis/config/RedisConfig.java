package com.example.redis.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.data.redis.RedisProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@RequiredArgsConstructor
public class RedisConfig {
    private final RedisProperties redisProperties;
    // 스프링에서 제공하는
    // 스프링이 기본으로 제공하는 객체가 아니라 config에서 설정한 정보를 셋팅해서 생성된 RedisTemplate을 이용하겠다고 정의

    @Bean
    @Primary
    public RedisTemplate<String,Object> getRedisTemplate(){
        RedisTemplate<String,Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory());
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        return redisTemplate;
    }

    // redis에 접속하기 위한 클라이언트 - jedis( 예전 방식 )
    // Lettuce를 이용해서 작업하는 것이 일반적

    @Bean
    public RedisConnectionFactory redisConnectionFactory(){
        return  new LettuceConnectionFactory(redisProperties.getHost(),redisProperties.getPort());
    }

}
