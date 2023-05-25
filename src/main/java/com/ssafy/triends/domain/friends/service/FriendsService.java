package com.ssafy.triends.domain.friends.service;

import com.ssafy.triends.domain.user.model.UserDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface FriendsService {
	void acceptFriend(Map<String, Object> notificationAndSenderAndUserId) throws Exception;
	List<UserDto> getFriendsList(int userId) throws Exception;
	void deleteFriend(int friendId, int userId) throws Exception;
	List<UserDto> getRecommendFriendsFromPreferenceSimilarity(int userId) throws Exception;
	List<UserDto> getRecommendFriendsListFromFriendsList(int userId) throws Exception;
	List<UserDto> searchFriendByName(Map<String, Object> keywordAndUserId) throws Exception;
}
