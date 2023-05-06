package com.ssafy.triends.domain.notice.mapper;

import com.ssafy.triends.domain.notice.model.NoticeDto;
import java.sql.SQLException;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface NoticeMapper{
	int register(NoticeDto noticeDto) throws SQLException;
	List<NoticeDto> list() throws SQLException;
	NoticeDto detail(int noticeId) throws SQLException;
	int modify(NoticeDto noticeDto) throws SQLException;
	int delete(int noticeId) throws SQLException;
}
