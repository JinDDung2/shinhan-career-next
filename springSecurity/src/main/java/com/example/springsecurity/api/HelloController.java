package com.example.springsecurity.api;

import com.example.springsecurity.memeber.entity.Member;
import com.example.springsecurity.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 토큰 테스트용: 로그인한 사용자 누구나 접근
 */

@RestController
public class HelloController {

    @GetMapping("/api/hello")
    public String hello(Authentication auth) {
        return auth.getName() + "님 안녕하세요. 권한: " + auth.getAuthorities();
    }
}
