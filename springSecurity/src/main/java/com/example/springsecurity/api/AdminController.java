package com.example.springsecurity.api;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * /api/admin/** 는 SecurityConfig에서 hasRole("ADMIN")으로 보호된다.
 * kim(일반) 토큰 → 403, lee(관리자) 토큰 → 200
 */

@RestController
@RequestMapping("/api/admin")
public class AdminController {

    @GetMapping("/hello")
    public String hello(Authentication auth) {
        return auth.getName() + " 관리자님, 관리자 API에 접근했습니다.";
    }
}
