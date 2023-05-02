package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.service.PlanService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PlanController {
	private final Logger logger = LoggerFactory.getLogger(PlanController.class);
	private PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}
	
}
