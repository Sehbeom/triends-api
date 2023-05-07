package com.ssafy.triends.domain.plan.model;

import com.ssafy.triends.domain.user.model.UserDto;

import java.util.List;

public class PlanDto {
	private int planId;
	private String title;
	private String startDate;
	private String endDate;
	private String thumbnail;
	private double centerLat;
	private double centerLng;
	private List<UserDto> members;
	private List<CourseDto> course;

	public int getPlanId() {
		return planId;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	public double getCenterLat() {
		return centerLat;
	}

	public void setCenterLat(double centerLat) {
		this.centerLat = centerLat;
	}

	public double getCenterLng() {
		return centerLng;
	}

	public void setCenterLng(double centerLng) {
		this.centerLng = centerLng;
	}

	public List<UserDto> getMembers() {
		return members;
	}

	public void setMembers(List<UserDto> members) {
		this.members = members;
	}

	public List<CourseDto> getCourse() {
		return course;
	}

	public void setCourse(List<CourseDto> course) {
		this.course = course;
	}

	@Override
	public String toString() {
		return "PlanDto{" +
				"planId=" + planId +
				", title='" + title + '\'' +
				", startDate='" + startDate + '\'' +
				", endDate='" + endDate + '\'' +
				", thumbnail='" + thumbnail + '\'' +
				", centerLat=" + centerLat +
				", centerLng=" + centerLng +
				", members=" + members +
				", course=" + course +
				'}';
	}
}
