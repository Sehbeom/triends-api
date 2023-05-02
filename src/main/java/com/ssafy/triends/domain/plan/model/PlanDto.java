package com.ssafy.triends.domain.plan.model;

import org.json.simple.JSONArray;

public class PlanDto {
	private int planId;
	private String title;
	private JSONArray members;
	private JSONArray course;
	private String startDate;
	private String endDate;
	private String thumbnail;

	public PlanDto() {

	}

	public PlanDto(int planId, String title, JSONArray members, JSONArray course, String startDate, String endDate,
			String thumbnail) {
		super();
		this.planId = planId;
		this.title = title;
		this.members = members;
		this.course = course;
		this.startDate = startDate;
		this.endDate = endDate;
		this.thumbnail = thumbnail;
	}

	public int getPlanId() {
		return planId;
	}

	public String getTitle() {
		return title;
	}

	public JSONArray getMembers() {
		return members;
	}

	public JSONArray getCourse() {
		return course;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getThumbnail() {
		return thumbnail;
	}

	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void setMembers(JSONArray members) {
		this.members = members;
	}

	public void setCourse(JSONArray course) {
		this.course = course;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public void setThumbnail(String thumbnail) {
		this.thumbnail = thumbnail;
	}

	@Override
	public String toString() {
		return "PlanDto [planId=" + planId + ", title=" + title + ", members=" + members + ", course=" + course
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", thumbnail=" + thumbnail + "]";
	}

}
