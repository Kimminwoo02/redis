package com.example.redis.service;


import java.util.Set;

public interface LowestPriceService {

    Set getZsetValue(String key);
}
