package com.ssafy.triends.domain.memo.mapper;

import org.apache.ibatis.annotations.Mapper;

import java.sql.SQLException;

@Mapper
public interface MemoMapper {
	int addMemo(int planId, int contentId, int userId, String content) throws SQLException;
	int deleteMemo(int memoId) throws SQLException;
}
