package com.ssafy.triends.domain.attraction.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SearchDto {
    private double latitude;
    private double longitude;
    private double range;
    private int contentTypeId;
    private int sidoCode;
    private int gugunCode;
    private String keyword;
}
