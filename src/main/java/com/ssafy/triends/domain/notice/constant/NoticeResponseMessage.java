package com.ssafy.triends.domain.notice.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NoticeResponseMessage {
    SEARCH_ALL_NOTICES_SUCCESS("공지사항 목록 조회가 완료되었습니다."),
    SEARCH_ONE_NOTICE_SUCCESS("공지사항 상세 정보 조회가 완료되었습니다."),
    CREATE_ONE_NOTICE_SUCCESS("공지사항 작성이 완료되었습니다."),
    UPDATE_ONE_NOTICE_SUCCESS("공지사항 수정이 완료되었습니다."),
    DELETE_ONE_NOTICE_SUCCESS("공지사항 삭제가 완료되었습니다.");

    private final String message;
}
