package com.example.redis.hash.repository;


import com.example.redis.hash.dto.Person2;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person2,String> {
}
