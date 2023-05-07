package com.ssafy.triends.domain.notice.service;

import com.ssafy.triends.domain.notice.model.NoticeDto;
import java.util.List;

public interface NoticeService {
	int register(NoticeDto noticeDto) throws Exception;
	List<NoticeDto> list() throws Exception;
	NoticeDto detail(int noticeId) throws Exception;
	int modify(NoticeDto noticeDto) throws Exception;
	int delete(int noticeId) throws Exception;
}
