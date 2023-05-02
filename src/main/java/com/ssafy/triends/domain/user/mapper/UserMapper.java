package com.ssafy.triends.domain.user.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;
	int joinUser(UserDto userDto) throws SQLException;
	int modifyUser(UserDto userDto) throws SQLException;
	UserDto getUser(String userId) throws SQLException;
	UserDto loginUser(Map<String, String> map) throws SQLException;
	int changePwd(Map<String, String> map) throws SQLException;
	int checkIdEmail(Map<String, String> map) throws SQLException;
	int resetPwd(Map<String, String> map) throws SQLException;
	List<UserDto> userList() throws SQLException;
	List<CommentDto> getComment(String userId);
	int deleteComment(int commentId) throws SQLException;
	int registPreference(List<Integer> listPreference) throws SQLException;
	List<Integer> getPreference(int userId) throws SQLException;
	int modifyPreference(int userId, List<Integer> listPreference) throws SQLException;
}

// String id, String pw
// int userId, String id, String pw
// String id, String email
// String id, String tmppwd