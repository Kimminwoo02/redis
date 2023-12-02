package com.example.redis.hash.controller;

import com.example.redis.hash.dto.Person2;
import com.example.redis.hash.service.PersonService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class PersonController {
    private final PersonService personService;


    @GetMapping("/person")
    public Person2 searchPerson(@RequestParam("key") String key){
        return personService.get(key);


    }

    @PostMapping("/person")
    public String createPerson(@RequestBody Person2 person2){
        personService.create(person2);
        return "Ok";

    }

    @PutMapping("/person")
    public String updatePerson(@RequestBody Person2 person2){
        personService.update(person2);
        return "Success";
    }


    @DeleteMapping("/person")
    public String deletePerson(@RequestParam("key") String key){
        personService.delete(key);
        return "YES";
    }
}
