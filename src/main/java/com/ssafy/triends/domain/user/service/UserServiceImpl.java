package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.mapper.UserMapper;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;
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
	public int idCheck(String userId) throws Exception {
		return userMapper.idCheck(userId);
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
	public int changePwd(Map<String, String> map) throws Exception {
		return userMapper.changePwd(map);
	}

	@Override
	public int checkIdEmail(Map<String, String> map) throws Exception {
		return userMapper.checkIdEmail(map);
	}

	@Override
	public int resetPwd(Map<String, String> map) throws Exception {
		return userMapper.resetPwd(map);
	}

	@Override
	public List<UserDto> userList() throws Exception {
		return userMapper.userList();
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
	public List<CommentDto> getComment(int userId) throws Exception {
		return userMapper.getComment(userId);
	}

	
	 @Override
	 public int deleteComment(int commentId) throws Exception {
		 return userMapper.deleteComment(commentId);
	 }

	@Override
	public int registPreferences(Map<String, Object> preferenceIds, int userId) throws Exception {
		logger.debug("preferenceIds : {}", preferenceIds.get("preferenceIds"));
		Map<String, Object> registPreferencesParameter = makeRegistPreferencesParameter(preferenceIds, userId);
		return userMapper.registPreferences(registPreferencesParameter);
	}

	@Override
	public int modifyPreferences(Map<String, Object> preferenceIds, int userId) throws Exception {
		userMapper.deletePreferences(userId);
		Map<String, Object> registPreferencesParameter = makeRegistPreferencesParameter(preferenceIds, userId);

		return userMapper.registPreferences(registPreferencesParameter);
	}

	@Override
	public UserPreferenceDto getOneUserPreferences(int userId) throws Exception {
		return userMapper.getOneUserPreferences(userId);
	}

	@Override
	public void deletePreference(int userId) {
		userMapper.deletePreferences(userId);
	}

	private Map<String, Object> makeRegistPreferencesParameter(Map<String, Object> preferenceIds, int userId) {
		preferenceIds.put("userId", userId);

		return preferenceIds;
	}
}
