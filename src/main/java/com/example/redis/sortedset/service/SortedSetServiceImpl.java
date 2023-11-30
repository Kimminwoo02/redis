package com.example.redis.sortedset.service;

import com.example.redis.sortedset.dto.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
@RequiredArgsConstructor
public class SortedSetServiceImpl implements SortedSetService {
    // 만료시간 설정

    private final RedisTemplate template;

    // sorted set 에 저장된 모든 값들( )
    public Set<String> getZSetValues(String key){
        ZSetOperations<String,String> zSet = template.opsForZSet();
        return (Set) zSet.rangeWithScores(key, 0, -1);

    }

    @Override
    public void createMember(Product newProduct) {
        ZSetOperations<String,String> zSet = template.opsForZSet();
        zSet.add(newProduct.getProductId(),newProduct.getCategoryId(), newProduct.getPrice());
    }

    @Override
    public int getRank(Product newProduct) {

        ZSetOperations<String,String> zSet = template.opsForZSet();
        return Math.toIntExact(zSet.rank(newProduct.getProductId(), newProduct.getCategoryId()));
    }


}
