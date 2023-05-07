package com.ssafy.triends.domain.notice.service;

import com.ssafy.triends.domain.notice.mapper.NoticeMapper;
import com.ssafy.triends.domain.notice.model.NoticeDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class NoticeServiceImpl implements NoticeService {
	private NoticeMapper noticeMapper;

	public NoticeServiceImpl(NoticeMapper noticeMapper) {
		super();
		this.noticeMapper = noticeMapper;
	}

	@Override
	public int register(NoticeDto noticeDto) throws Exception {
		return noticeMapper.register(noticeDto);
	}

	@Override
	public List<NoticeDto> list() throws Exception {
		return noticeMapper.list();
	}

	@Override
	public NoticeDto detail(int noticeId) throws Exception {
		return noticeMapper.detail(noticeId);
	}

	@Override
	public int modify(NoticeDto noticeDto) throws Exception {
		return noticeMapper.modify(noticeDto);
	}

	@Override
	public int delete(int noticeId) throws Exception {
		return noticeMapper.delete(noticeId);
	}
}
