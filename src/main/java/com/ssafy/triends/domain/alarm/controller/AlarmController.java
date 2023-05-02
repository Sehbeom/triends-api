package com.ssafy.triends.domain.alarm.controller;

import com.ssafy.triends.domain.alarm.service.AlarmService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AlarmController {
	private final Logger logger = LoggerFactory.getLogger(AlarmController.class);
	
	private AlarmService alarmService;

	public AlarmController(AlarmService alarmService) {
		super();
		this.alarmService = alarmService;
	}
	

}
