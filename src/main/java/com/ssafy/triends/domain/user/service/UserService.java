package com.ssafy.triends.domain.user.service;

import java.util.List;
import java.util.Map;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	int joinUser(UserDto userDto) throws Exception;
	int modifyUser(UserDto userDto) throws Exception;
	UserDto getUser(String userId) throws Exception;
	UserDto loginUser(Map<String, String> map) throws Exception;
	int changePwd(Map<String, String> map) throws Exception;
	int checkIdEmail(Map<String, String> map) throws Exception;
	int resetPwd(Map<String, String> map) throws Exception;
	List<UserDto> userList() throws Exception;
	List<CommentDto> getComment(String userId) throws Exception;
	int deleteComment(int commentId) throws Exception;
	int registPreference(List<Integer> listPreference) throws Exception;
	List<Integer> getPreference(int userId) throws Exception;
	int modifyPreference(int userId, List<Integer> listPreference) throws Exception;
}
