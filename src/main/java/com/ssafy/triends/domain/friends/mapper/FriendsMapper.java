package com.ssafy.triends.domain.friends.mapper;

import com.ssafy.triends.domain.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface FriendsMapper {
	int follow(int followerId, int followeeId) throws SQLException;
	int unfollow(int followerId, int followeeId) throws SQLException;
	List<UserDto> getFollowing(int followerId) throws SQLException;
}
