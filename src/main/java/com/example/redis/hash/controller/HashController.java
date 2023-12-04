//package com.example.redis.hash.controller;
//
//import com.example.redis.hash.dto.Subject;
//import com.example.redis.hash.service.SubjectHashService;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequiredArgsConstructor
//@Slf4j
//public class HashController {
//    // Hash에 데이터 ㅈ추가하는 컨트롤러 메소드
//    private final SubjectHashService subjectHashService;
//
//
//    @PostMapping("/hash1")
//    public String getHash(Subject subject){
//        subjectHashService.save(subject);
//
//        return "ok";
//    }
//
//    @GetMapping("/hash2")
//    public Subject getHash2(@RequestParam("key") String key){
//        log.info(key);
//        Subject byId = subjectHashService.findById(key);
//        System.out.println(byId.getId());
//        System.out.println(byId.getName());
//        System.out.println(byId.getInfo());
//        System.out.println(byId.getPrice());
//        return byId;
//
//    }
//
//
//}
