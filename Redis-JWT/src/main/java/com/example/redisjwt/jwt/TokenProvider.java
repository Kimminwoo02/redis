package com.example.redisjwt.jwt;

import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SecurityException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Component

public class TokenProvider implements InitializingBean {
    private final String secret;
    private final long tokenSecond;
    private Key key;


    public TokenProvider(@Value("${jwt.secret}") String secret,@Value("${jwt.token-validity-in-second}") long tokenSecond) {
        this.secret = secret;
        this.tokenSecond = tokenSecond;
    }

    // 빈이 생성되고 주입을 받은 후에
    // secret값을 base64 로 decode해서 key변수에 할당
    //HS512알고리즘 사용하는 경우 secret는 64b 이상을 이용해서 만들고 정의해야한다.

    @Override
    public void afterPropertiesSet() throws Exception {
        byte[] keydata = Decoders.BASE64.decode(secret);
        this.key = Keys.hmacShaKeyFor(keydata);
    }

    // 인증이 성공하면 Authentication에 포함된 권한정보를 가지고 토큰생성 이 토큰을 레디스에 저장하기 위해 작업하는 중
    // Authentication -> 토큰을 생성
    public String createToken(Authentication authentication){
        // 인증이 완료된 후 인증객체에 저장되어 있는 권한정보를 꺼내서 하나의 문자열을 만드는 작업
        String authoritylist = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(","));
        // 토큰만료시간
        long nowtime = new Date().getTime(); // 현재시간을 long으로 반환
        // 토큰이 생성된 시간을 기준으로 토큰의 만료시간을 설정
        Date targettime = new Date(nowtime + this.tokenSecond);

        return Jwts.builder()
                .setSubject(authentication.getName())
                .claim("auth",authoritylist) // 권한정보
                .signWith(key, SignatureAlgorithm.HS256)
                .setExpiration(targettime)
                .compact();


    }


    // 토큰에 담겨있는 정보를 이용해서 Authentication객체를 리턴하는 메소드
        public Authentication getAuthentication(String token){
            // 토큰을 이용해서 클레임객체를 생성
            Claims claims = Jwts
                    .parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();

            List<SimpleGrantedAuthority> authoritylist = Arrays.stream(claims.get("auth").toString().split(","))
                    // String 으로 저장된 권한을 spring security에서 자동으로 인식
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
            // 권한객체를 이용해서 최종적으로 Authentication 객체를 만들어서 작업하기
            // Authentication 객체에 저장할 스프링시큐리티의 내부에서 인식하는 User객체 생성

            User principal = new User(claims.getSubject(),"",authoritylist);
            return new UsernamePasswordAuthenticationToken(principal,token,authoritylist);


        }
    // 토큰을 validator 할 수 있는 메소드
    // 토큰의 유효성을 검증
    // io.jsonwebtoken라이브러리 내부에서 제공하는 Exception 객체를 활용

    public boolean validatorToken(String token){
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            return true; // 토큰파싱이 정상 처리 -> 문제없는 토큰
        }catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e){ // 문제가 있는 토큰
            System.out.println(e);
        }catch (ExpiredJwtException e){
            System.out.println("만료된 토큰!");
        }catch (UnsupportedJwtException e){
            System.out.println("지원되지 않는 토큰");
        }catch (IllegalArgumentException e){
            System.out.println("잘못된 토큰");
        }
        return false;
    }

}
