package com.example.redisjwt.config;

import com.example.redisjwt.jwt.CustomJwtFilter;
import com.example.redisjwt.jwt.JwtAccessDenied403Handler;
import com.example.redisjwt.jwt.JwtAuthntication401Check;
import com.example.redisjwt.jwt.TokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig {
    private final TokenProvider provider;
    private final JwtAuthntication401Check errorHandler1;
    private final JwtAccessDenied403Handler errorHandler2;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // HttpServletRequest를 사용하는 모든 요청에 대해서 접근권한을 설정하겠다는 의미

        http
                .csrf(CsrfConfigurer :: disable)
                .addFilterBefore(new CustomJwtFilter(provider),
                        UsernamePasswordAuthenticationFilter.class)
                .exceptionHandling(exception -> {
                    exception.authenticationEntryPoint(errorHandler1)
                            .accessDeniedHandler(errorHandler2);
                })
                .sessionManagement(session-> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests( request->{
                    request.requestMatchers(new AntPathRequestMatcher("/"),
                            new AntPathRequestMatcher("/api/hello"),
                            new AntPathRequestMatcher("/api/signin"),
                            new AntPathRequestMatcher("/api/signup")
                            )
                            .permitAll()
                            .anyRequest().authenticated();
                        }
                );
        return http.build();
    }



    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() {
        return (web) -> web.ignoring()
                .requestMatchers(new AntPathRequestMatcher("/h2-console/**"))
                .requestMatchers(new AntPathRequestMatcher("/favicon.ico"));
    }
}
