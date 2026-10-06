package com.example.springsecurity.memeber.entity;

import lombok.Getter;

@Getter
public enum MemberCode {
    USER("0"),
    ADMIN("1");

    private final String code;

    MemberCode(String code) {
        this.code = code;
    }

    public static MemberCode from(String code) {
        for (MemberCode memberCode : values()) {
            if (memberCode.code.equals(code)) {
                return memberCode;
            }
        }
        throw new IllegalArgumentException("Unknown member code: " + code);
    }

}
