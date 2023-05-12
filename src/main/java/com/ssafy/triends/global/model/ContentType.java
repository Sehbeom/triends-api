package com.ssafy.triends.global.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
@Getter
@RequiredArgsConstructor
public enum ContentType {
    TOURIST_ATTRACTION(12, "관광지"),
    CULTURAL_FACILITY(14, "문화시설"),
    FESTIVAL_PERFORMANCE_EVENT(15, "축제공연행사"),
    TRAVEL_COURSE(25, "여행코스"),
    LEPORTS(28, "레포츠"),
    LODGING(32, "숙박"),
    SHOPPING(38, "쇼핑"),
    RESTAURANT(39, "음식점");
    
    private final int contentTypeId;
    private final String name;

    @Override
    public String toString() {
        return name;
    }
}
