package com.ssafy.triends.domain.user.service;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.mapper.UserMapper;
import com.ssafy.triends.domain.user.model.UserDto;

import java.util.List;
import java.util.Map;

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
	public UserDto getUser(String userId) throws Exception {
		return userMapper.getUser(userId);
	}

	@Override
	public List<CommentDto> getComment(String userId) throws Exception {
		return userMapper.getComment(userId);
	}

	
	 @Override
	 public int deleteComment(int commentId) throws Exception {
		 return userMapper.deleteComment(commentId);
	 }
	 

	@Override
	public int registPreference(List<Integer> listPreference) throws Exception {
		return userMapper.registPreference(listPreference);
	}

	@Override
	public List<Integer> getPreference(int userId) throws Exception {
		return userMapper.getPreference(userId);
	}

	@Override
	public int modifyPreference(int userId, List<Integer> listPreference) throws Exception {
		return userMapper.modifyPreference(userId, listPreference);
	}

}
