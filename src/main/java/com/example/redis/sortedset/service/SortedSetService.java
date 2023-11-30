package com.example.redis.sortedset.service;

import com.example.redis.sortedset.dto.Product;

import java.util.Set;

public interface SortedSetService {
    Set<String> getZSetValues(String key);
    void createMember(Product newProduct);
    // 매개변수로 정의한 상품의 rank 구하기

    int getRank(Product newProduct);
}
