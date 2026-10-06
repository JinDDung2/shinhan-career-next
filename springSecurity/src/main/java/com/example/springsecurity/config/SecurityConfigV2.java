package com.example.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

//@Configuration
//@EnableWebSecurity // 웹 보안 활성화
public class SecurityConfigV2 { // react 사용 안하는 경우

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                // ③ URL별 접근 권한
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/", "/signup", "/error").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")   //
                        .anyRequest().authenticated())

                // ④ 폼 로그인
                .formLogin(form -> form
                        .defaultSuccessUrl("/hello", true)
                        .permitAll())

                // ⑤ 로그아웃
                .logout(logout -> logout.logoutSuccessUrl("/"))

                // ⑥ CSRF: Postman으로 회원가입 API를 호출하는 실습용 예외
                .csrf(csrf -> csrf.ignoringRequestMatchers("/signup"));

        return http.build();   // ⑦ 설정을 바탕으로 필터 체인 생성
    }

    @Bean
    public PasswordEncoder passwordEncoder() {   // ⑧ 비밀번호 암호화
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}
