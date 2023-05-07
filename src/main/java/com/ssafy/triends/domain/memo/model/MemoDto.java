package com.ssafy.triends.domain.memo.model;

public class MemoDto {
	private int memoId;
	private int userId;
	private int planId;
	private int contentId;
	private String content;

	public MemoDto() {

	}

	public MemoDto(int memoId, int userId, int planId, int contentId, String content) {
		super();
		this.memoId = memoId;
		this.userId = userId;
		this.planId = planId;
		this.contentId = contentId;
		this.content = content;
	}

	@Override
	public String toString() {
		return "MemoDto [memoId=" + memoId + ", userId=" + userId + ", planId=" + planId + ", contentId=" + contentId
				+ ", content=" + content + "]";
	}

	public int getMemoId() {
		return memoId;
	}

	public void setMemoId(int memoId) {
		this.memoId = memoId;
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

	public int getContentId() {
		return contentId;
	}

	public void setContentId(int contentId) {
		this.contentId = contentId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
