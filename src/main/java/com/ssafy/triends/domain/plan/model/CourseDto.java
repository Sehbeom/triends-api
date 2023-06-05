package com.ssafy.triends.domain.plan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@ApiModel(value = "CourseDto(플랜 내 여행지)", description = "일자에 소속된 코스(여행지) 정보를 담고 있다.")
public class CourseDto {
    @ApiModelProperty(value = "코스 pk")
    private int courseId;
    @ApiModelProperty(value = "코스가 소속된 일자 정보 (pk)")
    private int daysId;
    @ApiModelProperty(value = "코스의 여행지 정보 (pk)")
    private int contentId;
    @ApiModelProperty(value = "코스 시작 시간")
    private int startTime;
    @ApiModelProperty(value = "코스 종료 시간")
    private int endTime;

    @JsonProperty(value = "attractionInfo")
    @ApiModelProperty(value = "코스의 여행지 정보 (상세정보)")
    private AttractionDto attractionDto;

}
