package com.ssafy.triends.domain.memo.service;

import com.ssafy.triends.domain.memo.mapper.MemoMapper;
import org.json.simple.JSONArray;
import org.springframework.stereotype.Service;

@Service
public class MemoServiceImpl implements MemoService {

	private MemoMapper memoMapper;

	public MemoServiceImpl(MemoMapper memoMapper) {
		super();
		this.memoMapper = memoMapper;
	}

	@Override
	public int addMemo(int planId, int contentId, int userId, String content) throws Exception {
		return 0;
	}

	@Override
	public int deleteMemo(int memoId) throws Exception {
		return 0;
	}

	@Override
	public JSONArray getMemos(int planId, int contentId) throws Exception {
		return null;
	}
	

}
