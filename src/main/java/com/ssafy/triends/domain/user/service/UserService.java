package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import java.util.List;
import java.util.Map;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	int joinUser(UserDto userDto) throws Exception;
	int modifyUser(UserDto userDto) throws Exception;
	UserDto getUser(int userId) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	int changePwd(Map<String, String> map) throws Exception;
	int checkIdEmail(Map<String, String> map) throws Exception;
	int resetPwd(Map<String, String> map) throws Exception;
	List<UserDto> userList() throws Exception;
	List<CommentDto> getComment(int userId) throws Exception;
	int deleteComment(int commentId) throws Exception;
	int registPreferences(Map<String, Object> preferenceIds, int userId) throws Exception;
	int modifyPreferences(Map<String, Object> preferenceIds, int userId) throws Exception;
	UserPreferenceDto getOneUserPreferences(int userId) throws Exception;
	void deletePreference(int userId);
}
