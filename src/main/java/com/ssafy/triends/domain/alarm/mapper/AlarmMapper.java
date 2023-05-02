package com.ssafy.triends.domain.alarm.mapper;

import com.ssafy.triends.domain.alarm.model.AlarmDto;
import org.apache.ibatis.annotations.Mapper;
import org.json.simple.JSONArray;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface AlarmMapper {
	List<AlarmDto> getNotify(int userId, int page) throws SQLException;
	int invite(int planId, int ownerId, JSONArray invitelist) throws SQLException;
	int reply(int reviewId, int authorId) throws SQLException;
	int disable(int notifyId) throws SQLException;
}
