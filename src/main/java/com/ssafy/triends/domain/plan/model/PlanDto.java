package com.ssafy.triends.domain.plan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triends.domain.user.model.UserDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PlanDto {
	private int planId;
	private String title;
	private String startDate;
	private String endDate;
	private String thumbnail;
	private double centerLat;
	private double centerLng;
	private List<UserDto> members;
	@JsonProperty(value="courseInfo")
	private List<DayDto> days;

}
