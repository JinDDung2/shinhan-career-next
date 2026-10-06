package com.example.springsecurity.memeber.service;

import com.example.springsecurity.memeber.dto.SignupRequestDto;
import com.example.springsecurity.memeber.entity.Member;
import com.example.springsecurity.memeber.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public String signup(SignupRequestDto req) {
        if (memberRepository.existsByMemId(req.memId())) {
            throw new IllegalArgumentException("이미 존재하는 아이디");
        }
        String encoded = passwordEncoder.encode(req.password());
        return memberRepository.save(new Member(req.memId(), req.memNm(), encoded)).getMemId();
    }
}
