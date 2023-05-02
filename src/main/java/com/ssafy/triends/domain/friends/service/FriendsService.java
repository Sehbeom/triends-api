package com.ssafy.triends.domain.friends.service;

import com.ssafy.triends.domain.user.model.UserDto;

import java.util.List;

public interface FriendsService {
	int follow(int followerId, int followeeId) throws Exception;
	int unfollow(int followerId, int followeeId) throws Exception;
	List<UserDto> getFollowing(int followerId) throws Exception;
}
