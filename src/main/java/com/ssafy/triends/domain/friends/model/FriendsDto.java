package com.ssafy.triends.domain.friends.model;

public class FriendsDto {
	private int followId;
	private int followerId;
	private int followeeId;

	public FriendsDto() {
		super();
	}

	public FriendsDto(int followId, int followerId, int followeeId) {
		super();
		this.followId = followId;
		this.followerId = followerId;
		this.followeeId = followeeId;
	}

	public int getFollowId() {
		return followId;
	}

	public void setFollowId(int followId) {
		this.followId = followId;
	}

	public int getFollowerId() {
		return followerId;
	}

	public void setFollowerId(int followerId) {
		this.followerId = followerId;
	}

	public int getFolloweeId() {
		return followeeId;
	}

	public void setFolloweeId(int followeeId) {
		this.followeeId = followeeId;
	}

	@Override
	public String toString() {
		return "FollowDto [followId=" + followId + ", followerId=" + followerId + ", followeeId=" + followeeId + "]";
	}
}
