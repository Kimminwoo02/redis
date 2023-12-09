package com.example.redisjwt.dto;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import java.util.Collection;

// Redis에 저장할 로그인 사용자의 정보를 시큐리티 내부에서 인식할 수 있도록
// 저장하는 작업
public class MyUserDetail extends User {
    private UserDto userDto;

    public MyUserDetail(UserDto userDto, Collection<? extends GrantedAuthority> authorities) {
        super(userDto.getUserId(), userDto.getPassword(), authorities);
        this.userDto = userDto;
    }

    public UserDto getUserDto() {
        return userDto;
    }
}
