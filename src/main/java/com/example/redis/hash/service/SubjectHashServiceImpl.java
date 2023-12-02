package com.example.redis.hash.service;

import com.example.redis.hash.dto.Subject;
import com.example.redis.hash.repository.SubjectRedisRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SubjectHashServiceImpl implements SubjectHashService{

    private final SubjectRedisRepository subjectRedisRepository;
    @Override
    public void save(Subject subject) {
        subjectRedisRepository.save(subject);

    }

    @Override
    public Subject findById(String id) {
        return subjectRedisRepository.findById(id).get();
    }
}
