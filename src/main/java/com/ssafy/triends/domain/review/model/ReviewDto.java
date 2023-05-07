package com.ssafy.triends.domain.review.model;

public class ReviewDto {
	private int reviewId;
	private String subject;
	private int planId;
	private String planTitle;
	private String content;
	private int userId;
	private String userName;
	private String createdAt;
	private String modifiedAt;
	private int likes;
	private int scrapped;

	public String getPlanTitle() {
		return planTitle;
	}

	public void setPlanTitle(String planTitle) {
		this.planTitle = planTitle;
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
	public int getPlanId() {
		return planId;
	}
	public void setPlanId(int planId) {
		this.planId = planId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}

	public String getModifiedAt() {
		return modifiedAt;
	}

	public void setModifiedAt(String modifiedAt) {
		this.modifiedAt = modifiedAt;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public int getScrapped() {
		return scrapped;
	}

	public void setScrapped(int scrapped) {
		this.scrapped = scrapped;
	}

	@Override
	public String toString() {
		return "ReviewDto{" +
				"reviewId=" + reviewId +
				", subject='" + subject + '\'' +
				", planId=" + planId +
				", planTitle='" + planTitle + '\'' +
				", content='" + content + '\'' +
				", userId=" + userId +
				", userName='" + userName + '\'' +
				", createdAt='" + createdAt + '\'' +
				", modifiedAt='" + modifiedAt + '\'' +
				", likes=" + likes +
				", scrapped=" + scrapped +
				'}';
	}
}
