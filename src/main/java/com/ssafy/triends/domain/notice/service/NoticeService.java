package com.ssafy.triends.domain.notice.service;

import com.ssafy.triends.domain.notice.model.NoticeDto;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.sql.SQLException;

public interface NoticeService {
	int register(NoticeDto noticeDto) throws SQLException;
	JSONArray list(int page) throws SQLException;
	JSONObject view(int noticeId) throws SQLException;
	int modify(int noticeId, JSONObject obj) throws SQLException;
	int delete(int noticeId) throws SQLException;
	int total() throws SQLException;
}
