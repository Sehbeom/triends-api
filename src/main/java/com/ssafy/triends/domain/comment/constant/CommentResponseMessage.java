package com.ssafy.triends.domain.comment.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum CommentResponseMessage {
    GET_ALL_COMMENTS_OF_ONE_REVIEW_SUCCESS("해당 리뷰의 모든 댓글이 조회되었습니다."),
    GET_ALL_COMMENTS_OF_ONE_USER_SUCCESS("사용자가 작성한 모든 댓글이 조회되었습니다."),
    REGIST_ONE_COMMENT_SUCCESS("댓글이 등록되었습니다."),
    DELETE_ONE_COMMENT_SUCCESS("댓글이 삭제되었습니다.");

    private final String message;
}
