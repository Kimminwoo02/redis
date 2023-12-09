package com.example.redis.login.service;

import com.example.redis.sortedset.dto.BasicDto;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ExamService {
    // 만료시간 설정
    private final int LIMIT_TIME = 3 * 60;

    private final StringRedisTemplate template;

    public void createRedisValue(String Key){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.set(Key, String.valueOf(UUID.randomUUID()));

    }
    public String login(String key,String check){
        ValueOperations<String,String> valueOperations = template.opsForValue();
       String returnVal = "";
        if(Objects.equals(check, "ok") &&  valueOperations.get(key) == null){
            String value = valueOperations.get(key);
            returnVal = "로그인 성공";
        } else if (Objects.equals(check, "ok") && valueOperations.get(key)  != null) {
            returnVal = "로그인 성공";
        } else{
            returnVal = "로그인 실패";
        }
        return returnVal;
    }

//    public String getRedis(String key){
//        ValueOperations<String,Object> valueOperations = template.opsForValue();
//        return valueOperations.get(key);
//    }

    public void updateRedisValue(String data){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.getAndSet(data,String.valueOf(UUID.randomUUID()));
    }

    public void deleteRedisValue(String key){
        ValueOperations<String,String> valueOperations = template.opsForValue();
        valueOperations.getAndDelete(key);
    }



}
