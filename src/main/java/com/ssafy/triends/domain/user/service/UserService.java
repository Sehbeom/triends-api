package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import java.util.List;
import java.util.Map;

public interface UserService {

	int joinUser(UserDto userDto) throws Exception;
	int modifyUser(UserDto userDto) throws Exception;
	UserDto getUser(int userId) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	int registPreferences(Map<String, Object> userIdAndPreferenceIds) throws Exception;
	int modifyPreferences(Map<String, Object> userIdAndPreferenceIds) throws Exception;
	UserPreferenceDto getOneUserPreferences(int userId) throws Exception;
	void deletePreference(int userId);
	void saveRefreshToken(int userId, String refreshToken) throws Exception;
	void removeRefreshToken(int userId) throws Exception;
	String getRefreshToken(int userId) throws Exception;

}
