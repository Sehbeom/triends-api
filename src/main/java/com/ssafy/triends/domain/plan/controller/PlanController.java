package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.constant.PlanResponseMessage;
import com.ssafy.triends.domain.plan.service.PlanService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> list(HttpSession session) throws Exception {
        UserDto user = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.SEARCH_MY_PLANS_SUCCESS.getMessage(),
                        planService.getMyPlanList(user.getUserId())));

//		=== 테스트 ===
//		return ResponseEntity.ok(ResponseDto.createResponse("나의 플랜 목록 조회", planService.getMyPlanList(3)));
//		=============
    }

    @GetMapping("/{planId}")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> detail(@PathVariable("planId") int planId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.SEARCH_DETAIL_SUCCESS.getMessage(),
                        planService.getPlanDetail(planId)));
    }

    @DeleteMapping("/{planId}")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable("planId") String planId)
            throws Exception {
        planService.delete(Integer.parseInt(planId));
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.DELETE_SUCCESS.getMessage()));
    }

    @PostMapping("/create")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> create(@RequestBody Map<String, Object> planAndCourseInfo)
            throws Exception {
        planService.createPlan(planAndCourseInfo);
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.CREATE_SUCCESS.getMessage()));
    }

    @PostMapping("/member")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> memberAccept(@RequestBody Map<String, Object> planId, HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        planId.put("userId", userDto.getUserId());
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.ACCEPT_MEMBER_SUCCESS.getMessage(),
                        planService.acceptMember(planId)));

//      === 테스트 ===
//        planId.put("userId", 3);
//        return ResponseEntity.ok(
//                ResponseDto.createResponse(PlanResponseMessage.ACCEPT_MEMBER_SUCCESS.getMessage(),
//                        planService.acceptMember(planId)));
//      ============
    }

}
