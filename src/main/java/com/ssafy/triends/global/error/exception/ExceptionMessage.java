package com.ssafy.triends.global.error.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ExceptionMessage {
    USERINFO_NOT_FOUND("로그인이 필요한 서비스입니다."),
    TOKEN_EXPIRED("토큰이 만료되었습니다.");

    private final String message;
}
