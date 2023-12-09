//package com.example.redisjwt.controller;
//
//import com.example.redisjwt.dto.UserDto;
//import com.example.redisjwt.redis.RedisService;
//import jakarta.validation.Valid;
//import lombok.RequiredArgsConstructor;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//@RequiredArgsConstructor
//public class MissionController {
//    private final RedisService redisService;
//
//    @PostMapping("/mission")
//    public ResponseEntity<UserDto> login(@Valid @RequestBody UserDto userDto){
//        System.out.println("컨트롤러 타나요?");
//
//        redisService.save(userDto);
//
//        return new ResponseEntity<>(userDto,HttpStatus.OK);
//
//    }
//}
