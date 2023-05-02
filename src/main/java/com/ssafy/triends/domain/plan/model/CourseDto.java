package com.ssafy.triends.domain.plan.model;

import com.ssafy.triends.domain.attraction.model.AttractionDto;

public class CourseDto {
    private int courseId;
    private int planId;
    private int days;
    private int startTime;
    private int endTime;
    private AttractionDto attractionDto;

    public int getCourseId() {
        return courseId;
    }

    public void setCourseId(int courseId) {
        this.courseId = courseId;
    }

    public int getPlanId() {
        return planId;
    }

    public void setPlanId(int planId) {
        this.planId = planId;
    }

    public int getDays() {
        return days;
    }

    public void setDays(int days) {
        this.days = days;
    }

    public int getStartTime() {
        return startTime;
    }

    public void setStartTime(int startTime) {
        this.startTime = startTime;
    }

    public int getEndTime() {
        return endTime;
    }

    public void setEndTime(int endTime) {
        this.endTime = endTime;
    }

    public AttractionDto getContentId() {
        return attractionDto;
    }

    public void setContentId(AttractionDto attractionDto) {
        this.attractionDto = attractionDto;
    }
}
