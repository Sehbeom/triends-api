package com.ssafy.triends.domain.plan.constant;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanResponseMessage {
    SEARCH_MY_PLANS_SUCCESS("나의 플랜 목록 조회가 완료되었습니다."),
    SEARCH_DETAIL_SUCCESS("플랜 상세 정보 조회가 완료되었습니다."),
    DELETE_SUCCESS("플랜이 삭제되었습니다."),
    CREATE_SUCCESS("플랜이 생성되었습니다."),
    UPDATE_SUCCESS("플랜이 수정되었습니다."),
    ACCEPT_MEMBER_SUCCESS("멤버 초대 수락이 완료되었습니다."),
    GET_RECOMMEND_PLANS_SUCCESS("추천 플랜 목록 조회가 완료되었습니다.");

    private final String message;
}
