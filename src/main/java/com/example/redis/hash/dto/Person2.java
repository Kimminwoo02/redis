package com.example.redis.hash.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.redis.core.RedisHash;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RedisHash(value = "subject", timeToLive = 3000)
public class Person2 {
    private String id;
    private String ip;
    private LocalDateTime createDateTime;
    private String name;

}
