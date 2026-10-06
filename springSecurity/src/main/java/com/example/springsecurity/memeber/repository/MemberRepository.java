package com.example.springsecurity.memeber.repository;

import com.example.springsecurity.memeber.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MemberRepository extends JpaRepository<Member, Long> {
    Optional<Member> findByMemId(String memId);
    boolean existsByMemId(String memId);
}
