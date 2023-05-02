package com.ssafy.triends.domain.alarm.service;

import com.ssafy.triends.domain.alarm.model.AlarmDto;
import org.json.simple.JSONArray;

import java.util.List;

public interface AlarmService {
	List<AlarmDto> getNotify(int userId, int page) throws Exception;
	int invite(int planId, int ownerId, JSONArray invitelist) throws Exception;
	int reply(int reviewId, int authorId) throws Exception;
	int disable(int notifyId) throws Exception;
}
