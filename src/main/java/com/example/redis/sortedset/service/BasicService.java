package com.example.redis.sortedset.service;

import com.example.redis.sortedset.dto.BasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class BasicService {
    // 만료시간 설정
    private final int LIMIT_TIME = 3 * 60;

    private final RedisTemplate template;

    public void createRedisValue(BasicDto basicDto){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.set(basicDto.getKey(),basicDto.getValue());

        // 만료시간 설정
        // valueOperations.set(basicDto.getKey(),basicDto.getValue(),LIMIT_TIME);

    }

    public String getRedis(String key){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        return valueOperations.get(key);
    }

    public void updateRedisValue(BasicDto data){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.getAndSet(data.getKey(),data.getValue());
    }

    public void deleteRedisValue(String key){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.getAndDelete(key);
    }



}
