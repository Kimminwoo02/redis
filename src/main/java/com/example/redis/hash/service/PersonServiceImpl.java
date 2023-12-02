package com.example.redis.hash.service;

import com.example.redis.hash.dto.Person2;
import com.example.redis.hash.repository.PersonRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PersonServiceImpl implements PersonService{


    private final PersonRepository personRepository;
    @Override
    public void create(Person2 person) {
        personRepository.save(person);
    }

    @Override
    public Person2 get(String key) {
        return personRepository.findById(key).get();
    }

    @Override
    public void update(Person2 person2) {
        Person2 p = personRepository.findById(person2.getId()).get();
        if (person2.getIp() != null){
            p.setIp(person2.getIp());
        }
        if (person2.getName() != null){
            p.setName(person2.getName());
        }
        personRepository.save(p);


    }

    @Override
    public void delete(String key) {
        personRepository.deleteById(key);
    }
}
