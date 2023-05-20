package com.ssafy.triends.domain.attraction.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttractionResponseMessage {
    SEARCH_ATTRACTION_DETAIL_SUCCESS("여행지 상세정보 조회가 완료되었습니다."),
    SEARCH_ATTRACTIONS_SUCCESS("여행지 검색이 완료되었습니다."),
    GET_ATTRACTIONS_ORDER_BY_RATES_SUCCESS("평점순 여행지 목록 조회가 완료되었습니다."),
    GET_RECOMMEND_ATTRACTIONS_SUCCESS("사용자 취향 기반 여행지 목록 조회가 완료되었습니다.");

    private final String message;
}
