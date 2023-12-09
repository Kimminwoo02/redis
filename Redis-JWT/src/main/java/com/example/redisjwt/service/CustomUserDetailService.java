package com.example.redisjwt.service;

import com.example.redisjwt.dto.MyUserDetail;
import com.example.redisjwt.dto.UserDto;
import com.example.redisjwt.entity.MyUser;
import com.example.redisjwt.redis.UserCacheRepository;
import com.example.redisjwt.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;

import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailService")
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository; //DB에서 조회
    private final UserCacheRepository userCacheRepository; // redis에서 조회하기


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String id) throws UsernameNotFoundException {
        Long userId = Long.parseLong(id);
        // ------------
        // 1. Redis에서 사용자 정보를 조회
        // 2. Redis에 사용자 정보가 없으면 디비에서 조회하기
        // -------------
        UserDto user = userCacheRepository.getUser(id);
        if(user ==null){
            return  createUser(userId,userRepository.findById(userId).get());
        }
        List<GrantedAuthority> authorities = user.getAuthoritySet().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getAuthorityName()))
                        .collect(Collectors.toList());

        return new MyUserDetail(user,authorities);

        // MyUserDetial을 만들기 위해서 권한정보 추출


//        System.out.println(id);
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

        // 조회된 엔티티객체를 UserDto로 변환 - Redis에 저장하기 위해

        // UserDto와 권한정보를 이용해서 MyUserDto를 생성 - 스프링 시큐리티의 인증시스템
        // - 스프링 시큐리티의 인증시스템에서 자동으로 인식되도록 하기 위함
        //
        UserDetails resultUser = new User(String.valueOf(user.getUserId()),user.getPassword(),authorities);

        ModelMapper mapper = new ModelMapper();
        UserDto userDto = mapper.map(user, UserDto.class);
        return new MyUserDetail(userDto,authorities);
    }


}
