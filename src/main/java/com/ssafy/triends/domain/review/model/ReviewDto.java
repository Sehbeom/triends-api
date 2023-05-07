package com.ssafy.triends.domain.review.model;

public class ReviewDto {
	private int reviewId;
	private String subject;
	private int planId;
	private String content;
	private int userId;
	private String registTime;
	private float rating;
	private int ratingCnt;
	
	public int getRatingCnt() {
		return ratingCnt;
	}
	public void setRatingCnt(int ratingCnt) {
		this.ratingCnt = ratingCnt;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getRegistTime() {
		return registTime;
	}
	public void setRegistTime(String registTime) {
		this.registTime = registTime;
	}
	public float getRating() {
		return rating;
	}
	public void setRating(float rating) {
		this.rating = rating;
	}
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}
	@Override
	public String toString() {
		return "ReviewDto [reviewId=" + reviewId + ", subject=" + subject + ", planId=" + planId + ", content="
				+ content + ", userId=" + userId + ", registTime=" + registTime + ", rating=" + rating + ", ratingCnt="
				+ ratingCnt + "]";
	}
	
}
