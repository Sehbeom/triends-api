package com.ssafy.triends.domain.attraction.controller;

import com.ssafy.triends.domain.attraction.service.AttractionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AttractionController {
	private final Logger logger = LoggerFactory.getLogger(AttractionController.class);
	
	private AttractionService attractionInfoService;
	public AttractionController(AttractionService attractionInfoService) {
		super();
		this.attractionInfoService = attractionInfoService;
	}
	
}
