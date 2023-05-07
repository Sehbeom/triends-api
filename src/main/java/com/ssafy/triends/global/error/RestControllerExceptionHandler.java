package com.ssafy.triends.global.error;

import java.util.HashMap;
import java.util.Map;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class RestControllerExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception e) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("message", e.getMessage());
        return ResponseEntity.badRequest().body(errorMessage);
    }
}
