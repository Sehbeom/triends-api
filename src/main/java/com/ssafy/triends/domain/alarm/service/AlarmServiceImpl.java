package com.ssafy.triends.domain.alarm.service;

import com.ssafy.triends.domain.alarm.mapper.AlarmMapper;
import com.ssafy.triends.domain.alarm.model.AlarmDto;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlarmServiceImpl implements AlarmService {
	
	private AlarmMapper alarmMapper;

	public AlarmServiceImpl(AlarmMapper alarmMapper) {
		super();
		this.alarmMapper = alarmMapper;
	}

	@Override
	public List<AlarmDto> getNotify(int userId, int page) throws Exception {
		return null;
	}

	@Override
	public int invite(int planId, int ownerId, JSONArray invitelist) throws Exception {
		return 0;
	}

	@Override
	public int reply(int reviewId, int authorId) throws Exception {
		return 0;
	}

	@Override
	public int disable(int notifyId) throws Exception {
		return 0;
	}
	

}
