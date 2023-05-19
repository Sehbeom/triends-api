package com.ssafy.triends.domain.plan.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ssafy.triends.domain.attraction.model.AttractionDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDto {
    private int courseId;
    private int daysId;
    private int contentId;
    private int startTime;
    private int endTime;

    @JsonProperty(value = "attractionInfo")
    private AttractionDto attractionDto;

}
