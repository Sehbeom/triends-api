package com.ssafy.triends.domain.notification.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum NotificationResponseMessage {
    CREATE_MEMBER_INVITATION_SUCCESS("플랜 멤버 초대가 완료되었습니다."),
    CREATE_FRIEND_REQUEST_SUCCESS("친구 요청이 완료되었습니다."),
    SEARCH_NOTIFICATIONS_SUCCESS("알림 목록 조회가 완료되었습니다."),
    DELETE_NOTIFICATION_SUCCESS("알림이 삭제되었습니다.");

    private final String message;
}
