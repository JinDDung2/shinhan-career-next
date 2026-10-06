package com.example.springsecurity.auth;

import com.example.springsecurity.memeber.dto.LoginRequestDto;
import com.example.springsecurity.memeber.dto.TokenResponse;
import com.example.springsecurity.memeber.entity.Member;
import com.example.springsecurity.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * 로그인(토큰 발급)과 현재 사용자 조회
 */

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final MemberRepository memberRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;

    // POST /api/auth/login  {"username":"kim","password":"1234"}  →  {"accessToken":"eyJ..."}
    @PostMapping("/api/auth/login")
    public TokenResponse login(@RequestBody LoginRequestDto req) {
        // 인자 2개 생성자 = "인증 전" 상태 → CustomUserDetailsService + PasswordEncoder로 검증
        log.info("Login request: {}", req);
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.username(), req.password()));
        return new TokenResponse(jwtProvider.createAccessToken(auth));
    }

    // GET /api/me  (Authorization: Bearer <토큰>)
    @GetMapping("/api/me")
    public Map<String, Object> me(Authentication authentication) {
        Member member = memberRepository.findByMemId(authentication.getName()).orElseThrow();
        List<String> roles = authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .toList();
        return Map.of(
                "memId", member.getMemId(),
                "memNm", member.getMemNm(),
                "roles", roles
        );
    }

    // 아이디 없음·비밀번호 틀림 → 401 (어느 쪽이 틀렸는지는 알려 주지 않음)
    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<Map<String, String>> loginFailed(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Map.of("message", "아이디 또는 비밀번호가 올바르지 않습니다."));
    }

}
