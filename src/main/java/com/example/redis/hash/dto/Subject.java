package com.example.redis.hash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;

// hashes
// 만료시간을 주지 않는 경우 기본값 : -1 (만료시간이 없다.)
// timeToLive => ttl (만료시간 설정)

@Data
@NoArgsConstructor
@AllArgsConstructor
@RedisHash(value = "subject", timeToLive = 3000)
public class Subject {
    private String id;
    private String name;
    private int price;
    private String info;


}
