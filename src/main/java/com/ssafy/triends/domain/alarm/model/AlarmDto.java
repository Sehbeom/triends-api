package com.ssafy.triends.domain.alarm.model;

public class AlarmDto {

	private int notifyId;
	private int userId;
	private String content;
	private String type;
	private int read;
	private String url;

	public AlarmDto() {
		super();
	}

	public AlarmDto(int notifyId, int userId, String content, String type, int read, String url) {
		super();
		this.notifyId = notifyId;
		this.userId = userId;
		this.content = content;
		this.type = type;
		this.read = read;
		this.url = url;
	}

	public int getNotifyId() {
		return notifyId;
	}

	public void setNotifyId(int notifyId) {
		this.notifyId = notifyId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getRead() {
		return read;
	}

	public void setRead(int read) {
		this.read = read;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "NotifyDto [notifyId=" + notifyId + ", userId=" + userId + ", content=" + content + ", type=" + type
				+ ", read=" + read + ", url=" + url + "]";
	}

}
