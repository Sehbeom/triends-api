package com.ssafy.triends.domain.notice.controller;

import com.ssafy.triends.domain.notice.service.NoticeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class NoticeController {
	private final Logger logger = LoggerFactory.getLogger(NoticeController.class);
	private NoticeService noticeService;

	public NoticeController(NoticeService noticeService) {
		super();
		this.noticeService = noticeService;
	}

}
