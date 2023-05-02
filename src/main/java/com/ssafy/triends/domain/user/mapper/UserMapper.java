package com.ssafy.triends.domain.user.mapper;

import com.ssafy.triends.domain.user.model.UserDto;
import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;
	int joinMember(UserDto userDto) throws SQLException;
	UserDto loginMember(String id, String password) throws SQLException;
	int changePwd(int userId, String id, String pw) throws SQLException;
	int checkIdEmail(String id, String email) throws SQLException;
	int resetPwd(String id, String tmppwd) throws SQLException;
}
