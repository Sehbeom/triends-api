package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.user.model.UserDto;

public interface UserService {
	
	int idCheck(String userId) throws Exception;
	int joinMember(UserDto userDto) throws Exception;
	UserDto loginMember(String id, String password) throws Exception;
	int changePwd(int userId, String id, String pw) throws Exception;
	int checkIdEmail(String id, String email) throws Exception;
	int resetPwd(String id, String tmppwd) throws Exception;
}
