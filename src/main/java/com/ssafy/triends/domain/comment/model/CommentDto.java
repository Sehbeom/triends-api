package com.ssafy.triends.domain.comment.model;

public class CommentDto {
	private int commentId;
	private int reviewId;
	private int userId;
	private String createdAt;
	private String content;
	private String name;
	
	public int getCommentId() {
		return commentId;
	}
	public void setCommentId(int commentId) {
		this.commentId = commentId;
	}
	public int getReviewId() {
		return reviewId;
	}
	public void setReviewId(int reviewId) {
		this.reviewId = reviewId;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(String createdAt) {
		this.createdAt = createdAt;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "CommentDto [commentId=" + commentId + ", reviewId=" + reviewId + ", userId=" + userId + ", registTime="
				+ createdAt + ", content=" + content + ", name=" + name + "]";
	}
}
