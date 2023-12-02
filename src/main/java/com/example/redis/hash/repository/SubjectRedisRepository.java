package com.example.redis.hash.repository;

import com.example.redis.hash.dto.Subject;
import org.springframework.data.repository.CrudRepository;

public interface SubjectRedisRepository extends CrudRepository<Subject,String> {
}
