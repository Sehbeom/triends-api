package com.ssafy.triends.domain.memo.service;

import org.json.simple.JSONArray;

public interface MemoService {
	
	int addMemo(int planId, int contentId, int userId, String content) throws Exception;
	int deleteMemo(int memoId) throws Exception;
	JSONArray getMemos(int planId, int contentId) throws Exception;
	
}
