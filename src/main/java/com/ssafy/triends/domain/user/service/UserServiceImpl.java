package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.mapper.UserMapper;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	private UserMapper userMapper;
	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int joinUser(UserDto userDto) throws Exception {
		return userMapper.joinUser(userDto);
	}

	@Override
	public UserDto loginUser(Map<String, String> map) throws Exception {
		return userMapper.loginUser(map);
	}

	@Override
	public int modifyUser(UserDto userDto) throws Exception {
		return userMapper.modifyUser(userDto);
	}

	@Override
	public UserDto getUser(int userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public int registPreferences(Map<String, Object> userIdAndPreferenceIds) throws Exception {
		logger.debug("preferenceIds : {}", userIdAndPreferenceIds.get("preferenceIds"));
		return userMapper.registPreferences(userIdAndPreferenceIds);
	}

	@Override
	public int modifyPreferences(Map<String, Object> userIdAndPreferenceIds) throws Exception {
		userMapper.deletePreferences((Integer) userIdAndPreferenceIds.get("userId"));

		return userMapper.registPreferences(userIdAndPreferenceIds);
	}

	@Override
	public UserPreferenceDto getOneUserPreferences(int userId) throws Exception {
		return userMapper.getOneUserPreferences(userId);
	}

	@Override
	public void deletePreference(int userId) {
		userMapper.deletePreferences(userId);
	}

	@Override
	public void saveRefreshToken(int userId, String refreshToken) throws Exception {
		Map<String, Object> userIdAndToken = new HashMap<>();
		userIdAndToken.put("userId", userId);
		userIdAndToken.put("refreshToken", refreshToken);

		userMapper.saveRefreshToken(userIdAndToken);
	}

	@Override
	public void removeRefreshToken(int userId) throws Exception {
		userMapper.removeRefreshToken(userId);
	}

	@Override
	public String getRefreshToken(int userId) throws Exception {
		return userMapper.getRefreshToken(userId);
	}

}
