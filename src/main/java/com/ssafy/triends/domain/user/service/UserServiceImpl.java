package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.user.mapper.UserMapper;
import com.ssafy.triends.domain.user.model.UserDto;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService{
	private UserMapper userMapper;

	public UserServiceImpl(UserMapper userMapper) {
		super();
		this.userMapper = userMapper;
	}

	@Override
	public int idCheck(String userId) throws Exception {
		return 0;
	}

	@Override
	public int joinMember(UserDto userDto) throws Exception {
		return 0;
	}

	@Override
	public UserDto loginMember(String id, String password) throws Exception {
		return null;
	}

	@Override
	public int changePwd(int userId, String id, String pw) throws Exception {
		return 0;
	}

	@Override
	public int checkIdEmail(String id, String email) throws Exception {
		return 0;
	}

	@Override
	public int resetPwd(String id, String tmppwd) throws Exception {
		return 0;
	}
	
}
