package com.example.springsecurity.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;

/**
 * 회원가입 등 일반 API의 오류를 JSON으로 응답한다.
 * 주의: Exception 전체를 잡지 않는다. @PreAuthorize의 AccessDeniedException까지 잡으면
 *       Spring Security가 403으로 바꿔 주지 못하고 엉뚱한 응답이 나간다.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    // 아이디 중복 → 409
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String, String>> duplicate(IllegalArgumentException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(Map.of("message", e.getMessage()));
    }

    // @Valid 검증 실패 → 400
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> invalid(MethodArgumentNotValidException e) {
        String field = e.getBindingResult().getFieldErrors().isEmpty()
                ? "입력값"
                : e.getBindingResult().getFieldErrors().getFirst().getField();
        return ResponseEntity.badRequest().body(Map.of("message", field + " 값을 확인하세요."));
    }
}
