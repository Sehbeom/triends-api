package com.ssafy.triends.domain.friends.mapper;

import com.ssafy.triends.domain.user.model.UserDto;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FriendsMapper {
	void acceptFriend(Map<String, Object> receiverAndSenderId) throws SQLException;
	List<UserDto> getFriendsList(int userId) throws SQLException;
	void deleteFriend(Map<String, Object> userAndFriendId) throws SQLException;
	List<UserDto> getRecommendFriendsFromFriendsList(List<UserDto> friendsList) throws SQLException;
}
