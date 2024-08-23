package com.example.redis.service;


import com.example.redis.vo.Product;

import java.util.Set;

public interface LowestPriceService {

    Set getZsetValue(String key);
    int setNewProduct(Product newProduct);
}
