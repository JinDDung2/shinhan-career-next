package com.example.springsecurity.auth;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 토큰 발급과 검증 담당
 */
@Component
public class JwtProvider {

    private final SecretKey key;
    private final long accessExpirationMs;

    public JwtProvider(@Value("${jwt.secret}") String secret,
                       @Value("${jwt.access-expiration-ms}") long accessExpirationMs) {
        // 비밀키가 32바이트 미만이면 WeakKeyException으로 서버가 시작되지 않는다.
        this.key = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
        this.accessExpirationMs = accessExpirationMs;
    }

    // 인증 결과(아이디 + 권한)로 Access Token 생성
    public String createAccessToken(Authentication auth) {
        String roles = auth.getAuthorities().stream()
            .map(GrantedAuthority::getAuthority)          // ROLE_USER
            .collect(Collectors.joining(","));
        Date now = new Date();
        return Jwts.builder()
            .subject(auth.getName())                      // sub: 로그인 아이디(mem_id)
            .claim("roles", roles)                        // roles: 직접 정의한 클레임
            .issuedAt(now)                                // iat: 발급 시각
            .expiration(new Date(now.getTime() + accessExpirationMs))   // exp: 만료 시각
            .signWith(key)                                // 서명 (위조 방지)
            .compact();
    }

    // 토큰 검증 후 인증 객체 복원 (DB 조회 없음)
    public Authentication getAuthentication(String token) {
        Claims claims = Jwts.parser().verifyWith(key).build()
            .parseSignedClaims(token)                     // 형식 오류·서명 불일치·만료 시 JwtException
            .getPayload();
        List<SimpleGrantedAuthority> authorities = Arrays.stream(claims.get("roles", String.class).split(","))
            .map(SimpleGrantedAuthority::new)
            .toList();
        // 인자 3개 생성자 = "이미 인증된" 상태
        return new UsernamePasswordAuthenticationToken(claims.getSubject(), null, authorities);
    }
}
