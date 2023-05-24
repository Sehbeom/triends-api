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
    MODIFY_REVIEW("리뷰를 수정했습니다."),
    DELETE_REVIEW("리뷰가 삭제되었습니다."),
    ADD_LIKE_TO_REVIEW("좋아요가 적용되었습니다."),
    UNLIKE_TO_REVIEW("좋아요가 취소되었습니다."),
    INCREASE_REVIEW_SCRAPPED_SUCCESS("리뷰 스크랩 수가 증가되었습니다."),
    ;

    private final String message;
}
