package com.ssafy.triends.domain.friends.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FriendsResponseMessage {
    ACCEPT_FRIEND_SUCCESS("친구 요청 수락이 완료되었습니다."),
    GET_FRIENDS_LIST_SUCCESS("친구 목록 조회가 완료되었습니다."),
    DELETE_FRIEND_SUCCESS("친구 삭제가 완료되었습니다."),
    GET_RECOMMEND_FRIENDS_LIST_SUCCESS("추천 친구 목록 조회가 완료되었습니다."),
    SEARCH_FRIEND_SUCCESS("친구 검색이 완료되었습니다.");

    private final String message;
}
