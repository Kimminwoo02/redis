package com.example.redisjwt.service;

import com.example.redisjwt.entity.MyUser;
import com.example.redisjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Long userId = Long.parseLong(id);
        System.out.println(id);
        userRepository.findById(userId);
        return  userRepository.findById(userId)
                .map(userobj -> createUser(userId, userobj))
                .orElseThrow(() ->
                        new UsernameNotFoundException(userId + "사용자가 " + "데이터베이스에 없습니다"));
    }


    // 인증성공 후 Authentication 객체에 저장할 User객체가 필요 - User객체를 만들어서 리턴
    // loadUserByUsername 메소드의 리턴결과로 호출
    // 지금은 메소드 내부에서만 사용되므로
    private User createUser(Long id, MyUser user ){
        //권한
        List<GrantedAuthority> authorities = user.getAuthorityList().stream()
                .map( authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                .collect(Collectors.toList());

        return new User(String.valueOf(user.getUserId()),user.getPassword(),authorities);
    }
}
