package com.example.redisjwt.jwt;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.GenericFilterBean;

import java.io.IOException;
// 실제 필터에서 사용할 로직을 doFilter 안에 구현
@RequiredArgsConstructor
public class CustomJwtFilter extends GenericFilterBean {

    private final TokenProvider tokenProvider;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 클라이언트가 요청하면 토큰을 꺼내서 토큰의 유효성을 체크하고 SecurityContextHolder안에 토큰을 저장
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        String jwt = getToken(httpServletRequest);
        if(StringUtils.hasText(jwt) && tokenProvider.validatorToken(jwt) ){
            // 유효성체크가 완료되면 토큰을 이용해서 인증정보 꺼내기
            Authentication authentication = tokenProvider.getAuthentication(jwt);
            SecurityContextHolder.getContext().setAuthentication(authentication);
            System.out.println("인증된 사용자입니다. 인증정보를 저장합니다.");
        }
        else {
            System.out.println("인증되지 않은 사용자입니다. 토큰이 없습니다.");
        }
        chain.doFilter(request, response);
    }

    //클라이언트의 요청정보에서 토큰을 꺼내서 리턴하는 메소드
    public String getToken(HttpServletRequest request){
        String bearerToken = request.getHeader("Authorization");
        if(StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer " )){
            return bearerToken.substring(7);
        }
        return null;

    }
}
