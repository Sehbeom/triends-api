package com.ssafy.triends.domain.friends.service;

import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.friends.mapper.FriendsMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FriendsServiceImpl implements FriendsService {
	private FriendsMapper freindsMapper;

	public FriendsServiceImpl(FriendsMapper freindsMapper) {
		super();
		this.freindsMapper = freindsMapper;
	}

	@Override
	public int follow(int followerId, int followeeId) throws Exception {
		return 0;
	}

	@Override
	public int unfollow(int followerId, int followeeId) throws Exception {
		return 0;
	}

	@Override
	public List<UserDto> getFollowing(int followerId) throws Exception {
		return null;
	}

}
