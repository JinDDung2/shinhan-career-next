package com.example.springsecurity.auth;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/**
 * 매 요청의 Authorization 헤더에서 토큰을 꺼내 검증하고 SecurityContext에 넣는다.
 * - @Component를 붙이지 않는다: 빈으로 등록하면 서블릿 필터로 한 번 더 등록되어 중복 실행될 수 있음
 * - 토큰이 잘못돼도 여기서 401을 보내지 않는다: 막을지 말지는 인가 규칙이 결정
 */
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {

    private static final String BEARER_PREFIX = "Bearer ";

    private final JwtProvider jwtProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain chain) throws ServletException, IOException {
        String header = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (header != null && header.startsWith(BEARER_PREFIX)) {
            try {
                Authentication auth = jwtProvider.getAuthentication(header.substring(BEARER_PREFIX.length()));
                SecurityContextHolder.getContext().setAuthentication(auth);
            } catch (JwtException | IllegalArgumentException e) {
                SecurityContextHolder.clearContext();   // 인증 없이 진행 → 보호된 URL이면 401
            }
        }
        chain.doFilter(request, response);   // 토큰 유무와 상관없이 항상 다음 필터로
    }
}
