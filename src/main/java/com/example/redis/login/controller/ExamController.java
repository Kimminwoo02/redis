//package com.example.redis.login.controller;
//
//import com.example.redis.login.service.ExamService;
//import com.example.redis.sortedset.dto.BasicDto;
//import com.example.redis.sortedset.service.BasicService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.data.redis.core.ValueOperations;
//import org.springframework.http.HttpStatus;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.server.ResponseStatusException;
//
//import java.util.Objects;
//
//@RestController
//@RequiredArgsConstructor
//public class ExamController {
//    private final ExamService service;
//
//
//
//    @PostMapping("/login")
//    public String getRedisData( @RequestParam(value = "myId") String myId, @RequestParam(value = "check")String check){
//        return service.login(myId, check);
//
//
//    }
//
//    @PostMapping("/login1")
//    public String createUserData(String id){
//        service.createRedisValue(id);
//        return service.getRedis(id);
//    }
//
//    @PutMapping("/login")
//    public Boolean updateRedisData(String id){
//        service.updateRedisValue(id);
//        return true;
//    }
//
//    @DeleteMapping("/login")
//    public Boolean deleteRedisData(String id){
//        service.deleteRedisValue(id);
//        return true;
//    }
//
//}
