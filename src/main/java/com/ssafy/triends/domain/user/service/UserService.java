package com.ssafy.triends.domain.user.service;

import java.util.List;
import java.util.Map;
import java.util.Objects;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

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
	int registPreference(Map<String, Integer> map) throws Exception;
	List<Map<String, Integer>> getPreference(int userId) throws Exception;
	int modifyPreference(int userId, List<Integer> listPreference) throws Exception;
	void deletePreference(int userId);
}
