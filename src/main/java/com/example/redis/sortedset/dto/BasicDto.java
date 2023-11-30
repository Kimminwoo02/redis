package com.example.redis.sortedset.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.ValueOperations;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BasicDto {
    private String Key;
    private String score;
    private String value;


}
