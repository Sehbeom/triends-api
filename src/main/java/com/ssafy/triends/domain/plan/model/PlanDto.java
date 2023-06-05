package com.ssafy.triends.domain.plan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "PlanDto(플랜)", description = "플랜 상세 정보를 담고 있다.")
public class PlanDto {
	private int planId;
	private String title;
	private String startDate;
	private String endDate;
	private String thumbnail;
	private double centerLat;
	private double centerLng;
	@JsonProperty(value = "memberInfo")
	private List<PlanMemberDto> members;
	@JsonProperty(value = "courseInfo")
	private List<DayDto> days;

}
