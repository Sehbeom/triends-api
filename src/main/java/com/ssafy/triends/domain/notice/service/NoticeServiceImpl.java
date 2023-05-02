package com.ssafy.triends.domain.notice.service;

import com.ssafy.triends.domain.notice.mapper.NoticeMapper;
import com.ssafy.triends.domain.notice.model.NoticeDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

@Service
public class NoticeServiceImpl implements NoticeService {
	private NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		super();
		this.noticeMapper = noticeMapper;
	}

	@Override
	public int register(NoticeDto noticeDto) throws SQLException {
		return 0;
	}

	@Override
	public JSONArray list(int page) throws SQLException {
		return null;
	}

	@Override
	public JSONObject view(int noticeId) throws SQLException {
		return null;
	}

	@Override
	public int modify(int noticeId, JSONObject obj) throws SQLException {
		return 0;
	}

	@Override
	public int delete(int noticeId) throws SQLException {
		return 0;
	}

	@Override
	public int total() throws SQLException {
		return 0;
	}
	
}
