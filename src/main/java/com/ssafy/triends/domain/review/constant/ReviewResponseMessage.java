package com.ssafy.triends.domain.review.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ReviewResponseMessage {
    GET_ORDEREDREVIEWLIST("리뷰 리스트가 조회되었습니다."),
    GET_REVIEW_DETAIL("리뷰 정보 조회가 완료되었습니다."),
    REGIST_REVIEW("리뷰 등록이 완료되었습니다."),
    REGIST_COMMENT("댓글 등록이 완료되었습니다."),
    GET_MY_REVIEW("내가 쓴 리뷰를 조회했습니다."),
    MODIFY_REVIEW("리뷰를 수정했습니다.")
    ;
//    LOGIN_SUCCESS("로그인 성공했습니다."),
//    LOGOUT_SUCCESS("로그아웃 되었습니다."),
//    GET_USER_INFO("유저 정보 조회가 완료되었습니다."),
//    JOIN_USER("회원가입이 완료되었습니다."),
//    MODIFY_USER_INFO("유저 정보 수정가 수정되었습니다."),
//    GET_USER_COMMENT("작성한 댓글 조회가 완료되었습니다."),
//    DELETE_USER_COMMENT("댓글이 삭제되었습니다."),
//    REGISTER_PREFERENCE("선호 카테고리가 등록되었습니다."),
//    MODIFY_PREFERENCE("선호 카테고리가 수정되었습니다.");

    private final String message;
}
