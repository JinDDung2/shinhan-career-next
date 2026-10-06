package com.example.springsecurity.config;

import com.example.springsecurity.auth.JwtAuthenticationFilter;
import com.example.springsecurity.auth.JwtProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.hierarchicalroles.RoleHierarchy;
import org.springframework.security.access.hierarchicalroles.RoleHierarchyImpl;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

/**
 * JWT(Stateless) 보안 설정
 * - 세션을 만들지 않고, 매 요청의 Authorization: Bearer 토큰으로 인증한다.
 * - 쿠키를 쓰지 않으므로 CSRF 보호를 끈다.
 */

@Configuration
@EnableWebSecurity
@EnableMethodSecurity   // @PreAuthorize 활성화 (모듈 3)
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http, JwtProvider jwtProvider) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .formLogin(f -> f.disable())
                .httpBasic(b -> b.disable())

                // 인가 규칙: 위에서부터 첫 일치 규칙 적용, anyRequest()는 마지막
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/api/auth/**", "/signup", "/error").permitAll()
                        .requestMatchers("/api/admin/**").hasRole("ADMIN")
                        .anyRequest().authenticated())

                // 401: 토큰 없음·만료·위조, 로그인 실패 / 403: 권한 부족
                .exceptionHandling(e -> e
                        .authenticationEntryPoint((req, res, ex) -> res.sendError(401, "인증 필요"))
                        .accessDeniedHandler((req, res, ex) -> res.sendError(403, "권한 없음")))

                // 인가 검사 전에 토큰으로 SecurityContext를 채운다.
                .addFilterBefore(new JwtAuthenticationFilter(jwtProvider),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // 로그인 API(AuthController)에서 아이디·비밀번호 검증에 사용
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // 기본 BCrypt, 저장 시 {bcrypt} 접두어 부여 ({noop} 초기 데이터도 검증 가능)
    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    // 계층형 권한: ADMIN은 USER 권한을 자동으로 포함 (모듈 3)
    @Bean
    static RoleHierarchy roleHierarchy() {
        return RoleHierarchyImpl.withDefaultRolePrefix()
                .role("ADMIN").implies("USER")
                .build();
    }
}
