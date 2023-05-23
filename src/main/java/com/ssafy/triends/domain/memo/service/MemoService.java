package com.ssafy.triends.domain.memo.service;


public interface MemoService {
	
	int addMemo(int planId, int contentId, int userId, String content) throws Exception;
	int deleteMemo(int memoId) throws Exception;
	
}
