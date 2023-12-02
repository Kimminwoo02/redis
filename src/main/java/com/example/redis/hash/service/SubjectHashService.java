package com.example.redis.hash.service;

import com.example.redis.hash.dto.Subject;

public interface SubjectHashService {
    void save(Subject subject);

    Subject findById(String id);


}
