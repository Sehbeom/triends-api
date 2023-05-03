package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.service.PlanService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.dto.ResponseDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
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

	@PostMapping("/invite")
	public ResponseEntity<ResponseDto<?>> inviteMember(@RequestParam Map<String, String> userAndPlanId, HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		userAndPlanId.put("userId", Integer.toString(userDto.getUserId()));
//		=== 테스트 ===
//		userAndPlanId.put("userId", "8");
//		=============
		planService.inviteMember(userAndPlanId);
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 멤버 초대 요청 완료"));
	}

	@DeleteMapping("/{planId}")
	public ResponseEntity<ResponseDto<?>> delete(@PathVariable("planId") String planId) throws Exception {
		planService.delete(Integer.parseInt(planId));
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 삭제 완료"));
	}

}
