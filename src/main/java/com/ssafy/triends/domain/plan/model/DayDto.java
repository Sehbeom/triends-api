package com.ssafy.triends.domain.plan.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "DayDto(플랜 내 일자)", description = "플랜에 소속된 일자 정보를 담고 있다.")
public class DayDto {
    @ApiModelProperty(value = "일자 pk")
    private int daysId;
    @ApiModelProperty(value = "일자가 소속된 플랜 정보 (pk)")
    private int planId;
    @ApiModelProperty(value = "일자 정보 (n일차)")
    private int dayInfo;

    @ApiModelProperty(value = "일자에 소속된 코스 목록")
    private List<CourseDto> courses;
}
