package com.ssafy.triends.domain.memo.controller;

import com.ssafy.triends.domain.memo.service.MemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemoController {
	private final Logger logger = LoggerFactory.getLogger(MemoController.class);
	private MemoService memoService;

	public MemoController(MemoService memoService) {
		super();
		this.memoService = memoService;
	}

}
