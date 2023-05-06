package com.ssafy.triends.domain.attraction.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum AttractionResponseMessage {
    SEARCH_ATTRACTION_DETAIL_SUCCESS("여행지 상세정보 조회가 완료되었습니다."),
    SEARCH_ATTRACTIONS_SUCCESS("여행지 검색이 완료되었습니다.");

    private final String message;
}
