package com.example.redisjwt.controller;

import com.example.redisjwt.dto.LoginDTO;
import com.example.redisjwt.jwt.TokenProvider;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class LoginController {
    
    // 인증이 성공하면 토큰을 만들어서 응답메세지에 넘기기위해서 필요
    private final TokenProvider tokenProvider;

    //스프링 시큐리티의 인증시스템이 동작
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    @PostMapping("/signin")
    public ResponseEntity<String> login(@Valid @RequestBody LoginDTO loginDTO){
        
        // 인증처리
        //1. 사용자정보를 담은 UsernamePasswordAuthenticationToken 객체를 생성
        UsernamePasswordAuthenticationToken authenticationToken = 
                new UsernamePasswordAuthenticationToken(loginDTO.getUser(),loginDTO.getPassword());
        
        //2.  SpringSecurity의 인증 처리 시스템이 동작할 수 있도록 호출
        // => 인증이 완료되면 인증된 정보가 usernamePasswordAuthenticationToken으로 전송
        Authentication authentication = authenticationManagerBuilder.getObject().authenticate(authenticationToken);

        //3. 인증객체를 이용해서 토큰 생성
        String jwt = tokenProvider.createToken(authentication);


        //4. 응답헤더에 토큰을 보내기
        HttpHeaders httpHeaders = new HttpHeaders(); 
        httpHeaders.add("Authorization","Bearer "+ jwt);

        return new ResponseEntity<>(jwt,httpHeaders, HttpStatus.OK);
        
    }
}
