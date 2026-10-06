package com.example.springsecurity.memeber.controller;

import com.example.springsecurity.memeber.entity.Member;
import com.example.springsecurity.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * 메서드 보안 예제 (모듈 3)
 * 인가 코드는 인증 방식(세션/JWT)과 무관하게 그대로 동작한다.
 * authentication.name = 토큰의 sub(mem_id)
 */
@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberApiController {

    private final MemberRepository memberRepository;

    // 관리자만 전체 목록 조회
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping
    public List<String> findAll() {
        return memberRepository.findAll().stream()
            .map(Member::getMemId)
            .toList();
    }

    // 본인 또는 관리자만 조회
    @PreAuthorize("#memId == authentication.name or hasRole('ADMIN')")
    @GetMapping("/{memId}")
    public Map<String, String> findOne(@PathVariable String memId) {
        Member member = memberRepository.findByMemId(memId).orElseThrow();
        return Map.of(
            "memId", member.getMemId(),
            "memNm", member.getMemNm()
        );
    }
}
