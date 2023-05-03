package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.model.CourseDto;
import com.ssafy.triends.domain.plan.model.PlanDto;
import com.ssafy.triends.domain.plan.service.PlanService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

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
		UserDto user = (UserDto) session.getAttribute("userDto");
		return ResponseEntity.ok(ResponseDto.createResponse("나의 플랜 목록 조회", planService.getMyPlanList(user.getUserId())));

//		=== 테스트 ===
//		return ResponseEntity.ok(ResponseDto.createResponse("나의 플랜 목록 조회", planService.getMyPlanList(3)));
//		=============
	}

	@GetMapping("/{planId}")
	public ResponseEntity<ResponseDto<?>> detail(@PathVariable("planId") int planId) throws Exception {
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 상세 조회", planService.getPlanWithCourse(planId)));
	}

	@DeleteMapping("/{planId}")
	public ResponseEntity<ResponseDto<?>> delete(@PathVariable("planId") String planId) throws Exception {
		planService.delete(Integer.parseInt(planId));
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 삭제 완료"));
	}

	@PostMapping("/create")
	public ResponseEntity<ResponseDto<?>> create(@RequestBody Map<String, Object> planAndCourse) throws Exception {
		planService.createPlanAndCourses(planAndCourse);
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 생성 완료"));
	}

}
