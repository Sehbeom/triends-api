package com.ssafy.triends.domain.attraction.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "SearchDto(여행지 검색 내용)", description = "여행지 검색 과정에서 사용자가 입력한 정보를 담고 있다.")
public class SearchDto {
    @ApiModelProperty(value = "남서쪽 모서리 위도 (현재 렌더링 중인 지도 범위 확인)")
    private double swLat;
    @ApiModelProperty(value = "남서쪽 모서리 경도 (현재 렌더링 중인 지도 범위 확인)")
    private double swLng;
    @ApiModelProperty(value = "북동쪽 모서리 위도 (현재 렌더링 중인 지도 범위 확인)")
    private double neLat;
    @ApiModelProperty(value = "북동쪽 모서리 경도 (현재 렌더링 중인 지도 범위 확인)")
    private double neLng;
    @ApiModelProperty(value = "여행지 유형 정보 (pk)")
    private int contentTypeId;
    @ApiModelProperty(value = "여행지 시/도 코드 (pk)")
    private int sidoCode;
    @ApiModelProperty(value = "여행지 시/군/구 코드 (pk)")
    private int gugunCode;
    @ApiModelProperty(value = "검색어")
    private String keyword;
}
