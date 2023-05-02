package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.service.PlanService;
import com.ssafy.triends.global.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/plan")
public class PlanController {
	private final Logger logger = LoggerFactory.getLogger(PlanController.class);
	private PlanService planService;

	public PlanController(PlanService planService) {
		super();
		this.planService = planService;
	}

	@GetMapping("/list")
	public ResponseEntity<ResponseDto<?>> list(HttpSession session) throws Exception {
//		UserDto user = (UserDto) session.getAttribute("userDto");
//		return ResponseEntity.ok(ResponseDto.createResponse("나의 플랜 목록 조회", planService.getMyPlanList(user.getUserId())));
		return ResponseEntity.ok(ResponseDto.createResponse("나의 플랜 목록 조회", planService.getMyPlanList(3)));
	}

	@GetMapping("/{planId}")
	public ResponseEntity<ResponseDto<?>> detail(@PathVariable("planId") int planId) throws Exception {
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 상세 조회", planService.getPlanWithCourse(planId)));
	}
}
