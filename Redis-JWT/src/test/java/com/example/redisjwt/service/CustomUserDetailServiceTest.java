package com.example.redisjwt.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest
@Transactional
class CustomUserDetailServiceTest {
    @Autowired
    CustomUserDetailService service;


    @Test
    @DisplayName("UserDetailService")
    void test1(){
        UserDetails userDetails = service.loadUserByUsername("1");
        System.out.println(userDetails);
    }

}