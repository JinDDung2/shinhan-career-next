package com.example.springsecurity.memeber.entity;

import com.example.springsecurity.converter.MemberCodeConverter;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tb_mem")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

    @Id
    @Column(name = "mem_id")
    private String memId;          // 아이디가 곧 PK (자동 생성 아님)

    @Column(name = "mem_nm", nullable = false)
    private String memNm;

    @Column(name = "mem_cd")
    @Convert(converter = MemberCodeConverter.class)
    private MemberCode memCd;          // "0" 일반사용자, "1" 관리자

    @Column(name = "pwd", nullable = false)
    private String pwd;            // 반드시 암호화된 값

    @Column(name = "profile_img")
    private String profileImg;

    public Member(String memId, String memNm, String encodedPwd) {
        this.memId = memId;
        this.memNm = memNm;
        this.pwd = encodedPwd;
        this.memCd = MemberCode.USER;          // 회원가입은 항상 일반사용자
    }

    // DB 코드값 → Spring Security 역할 이름
    public MemberCode getRole() {
        return memCd;
    }
}
