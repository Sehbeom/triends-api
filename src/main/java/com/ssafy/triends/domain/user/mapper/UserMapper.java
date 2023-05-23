package com.ssafy.triends.domain.user.mapper;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.model.UserPreferenceDto;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
	int idCheck(String id) throws SQLException;
	int joinUser(UserDto userDto) throws SQLException;
	int modifyUser(UserDto userDto) throws SQLException;
	UserDto getUser(int userId) throws SQLException;
	UserDto loginUser(Map<String, String> map) throws SQLException;
	int changePwd(Map<String, String> map) throws SQLException;
	int checkIdEmail(Map<String, String> map) throws SQLException;
	int resetPwd(Map<String, String> map) throws SQLException;
	List<UserDto> userList() throws SQLException;
	List<CommentDto> getComment(int userId);
	int deleteComment(int commentId) throws SQLException;
	int registPreferences(Map<String, Object> preferenceIdsAndUserId) throws SQLException;
	UserPreferenceDto getOneUserPreferences(int userId) throws SQLException;
	List<UserPreferenceDto> getAllOtherUsersPreferences(int userId) throws SQLException;
	void deletePreferences(int userId);
	void saveRefreshToken(Map<String, Object> userIdAndToken) throws SQLException;
	void removeRefreshToken(int userId) throws SQLException;
}