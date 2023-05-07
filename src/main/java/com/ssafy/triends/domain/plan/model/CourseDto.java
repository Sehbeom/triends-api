package com.ssafy.triends.domain.plan.model;

import com.ssafy.triends.domain.attraction.model.AttractionDto;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CourseDto {
    private int courseId;
    private int planId;
    private int days;
    private int startTime;
    private int endTime;
    private int contentId;
    private AttractionDto attractionDto;

}
