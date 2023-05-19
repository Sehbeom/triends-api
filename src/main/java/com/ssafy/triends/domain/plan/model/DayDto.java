package com.ssafy.triends.domain.plan.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class DayDto {
    private int daysId;
    private int planId;
    private int dayInfo;

    private List<CourseDto> courses;
}
