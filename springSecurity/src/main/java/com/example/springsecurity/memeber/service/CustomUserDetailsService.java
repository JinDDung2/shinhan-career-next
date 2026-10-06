package com.example.springsecurity.memeber.service;

import com.example.springsecurity.memeber.entity.Member;
import com.example.springsecurity.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * 로그인 아이디로 tb_mem을 조회해 Spring Security의 UserDetails로 변환한다.
 * 비밀번호 비교는 하지 않는다 (PasswordEncoder가 담당).
 * JWT 방식에서는 로그인 API(/api/auth/login)에서만 호출되고, 이후 요청은 토큰으로 인증된다.
 */

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) {

        log.info("loadUserByUsername: " + username);

        Member member = memberRepository.findByMemId(username)
                .orElseThrow(() -> new UsernameNotFoundException("사용자 없음: " + username));

        log.info("member: " + member);
        log.info("password: " + member.getPwd());
        log.info("role: " + member.getRole());
        return User.withUsername(member.getMemId())
                .password(member.getPwd())
                .roles(member.getRole().name())   // "1" → ROLE_ADMIN, "0" → ROLE_USER
                .build();
    }
}
