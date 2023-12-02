package com.example.redis.hash.service;

import com.example.redis.hash.dto.Person2;


public interface PersonService {
    void create(Person2 person);

    Person2 get(String key);

    void update(Person2 person2);

    void delete(String key);
}
