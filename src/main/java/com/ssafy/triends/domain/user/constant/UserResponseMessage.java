package com.ssafy.triends.domain.user.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum UserResponseMessage {
    LOGIN_SUCCESS("로그인 성공했습니다."),
    LOGIN_FAIL("로그인에 실패하였습니다."),
    LOGOUT_SUCCESS("로그아웃 되었습니다."),
    GET_USER_INFO("유저 정보 조회가 완료되었습니다."),
    JOIN_USER_SUCCESS("회원가입이 완료되었습니다."),
    JOIN_USER_FAIL("회원가입에 실패하였습니다."),
    MODIFY_USER_INFO("유저 정보 수정가 수정되었습니다."),
    GET_USER_COMMENT("작성한 댓글 조회가 완료되었습니다."),
    DELETE_USER_COMMENT("댓글이 삭제되었습니다."),
    GET_PREFERENCE("선호 카테고리가 조회되었습니다."),
    REGISTER_PREFERENCE("선호 카테고리가 등록되었습니다."),
    MODIFY_PREFERENCE("선호 카테고리가 수정되었습니다."),
    DELETE_PREFERENCE("선호 카테고리가 모두 삭제되었습니다."),
    AUTHORIZATION_SUCCESS("사용자 정보가 인증되었습니다."),
    ACCESS_TOKEN_REISSUE_SUCCESS("토큰 재발급이 완료되었습니다.");

    private final String message;
}
