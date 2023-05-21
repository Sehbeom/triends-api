package com.ssafy.triends.domain.plan.controller;

import com.ssafy.triends.domain.plan.constant.PlanResponseMessage;
import com.ssafy.triends.domain.plan.service.PlanService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

@RestController
@RequestMapping("/plan")
@Api(tags = {"플랜 관리"})
public class PlanController {

    private final Logger logger = LoggerFactory.getLogger(PlanController.class);
    private PlanService planService;

    public PlanController(PlanService planService) {
        super();
        this.planService = planService;
    }

    @GetMapping("")
    @LoginRequired
    @ApiOperation(value = "플랜 추천 목록 조회", notes = "지도 상에 포함되는 플랜 추천 목록 조회 (로그인 필요)")
    @ApiImplicitParam(name = "latLngInfo", value = "swLat, swLng, neLat, neLng 값 전달 필요", dataTypeClass = Map.class, defaultValue = "{\"swLat\":35.0,\"swLng\":129.0,\"neLat\":36.0,\"neLng\":130.0}")
    public ResponseEntity<ResponseDto<?>> getRecommendPlans(@RequestParam Map<String, Object> latLngInfo) throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.GET_RECOMMEND_PLANS_SUCCESS.getMessage(),
                        planService.getRecommendPlans(latLngInfo)));
    }

    @GetMapping("/list")
    @LoginRequired
    @ApiOperation(value = "나의 플랜 목록 조회", notes = "사용자가 멤버로 참여 중인 플랜 목록 조회 (로그인 필요)")
    public ResponseEntity<ResponseDto<?>> list(@ApiIgnore HttpSession session) throws Exception {
        UserDto user = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.SEARCH_MY_PLANS_SUCCESS.getMessage(),
                        planService.getMyPlanList(user.getUserId())));
    }

    @GetMapping("/{planId}")
    @LoginRequired
    @ApiOperation(value = "플랜 상세 정보 조회", notes = "플랜 상세 정보 조회 (로그인 필요)")
    @ApiImplicitParam(name = "planId", value = "조회할 플랜 아이디", defaultValue = "1", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> detail(@PathVariable("planId") int planId)
            throws Exception {
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.SEARCH_DETAIL_SUCCESS.getMessage(),
                        planService.getPlanDetail(planId)));
    }

    @DeleteMapping("/{planId}")
    @LoginRequired
    @ApiOperation(value = "플랜 삭제", notes = "플랜 삭제 (로그인 필요)")
    @ApiImplicitParam(name = "planId", value = "삭제할 플랜 아이디", defaultValue = "1", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> delete(@PathVariable("planId") String planId)
            throws Exception {
        planService.delete(Integer.parseInt(planId));
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.DELETE_SUCCESS.getMessage()));
    }

    @PostMapping("/create")
    @LoginRequired
    @ApiOperation(value = "플랜 생성", notes = "플랜 생성 (로그인 필요)")
    @ApiImplicitParam(name = "planAndCourseInfo", value = "플랜 및 일자별 코스 정보 전달 필요 (포맷은 노션 참고)", dataTypeClass = Map.class)
    public ResponseEntity<ResponseDto<?>> create(@RequestBody Map<String, Object> planAndCourseInfo, @ApiIgnore HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());

        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.CREATE_SUCCESS.getMessage(),
                        planService.createPlan(planAndCourseInfo, userDto.getUserId())));
    }

    @PostMapping("/member")
    @LoginRequired
    @ApiOperation(value = "멤버 초대 수락", notes = "멤버 초대 요청을 수락한다. (로그인 필요)")
    @ApiImplicitParam(name = "notificationAndPlanId",
            value = "멤버 초대 알림 아이디 및 플랜 아이디",
            dataTypeClass = Map.class,
            defaultValue = "{\"notificationId\":5,\"planId\":3}")
    public ResponseEntity<ResponseDto<?>> memberAccept(@RequestBody Map<String, Object> notificationAndPlanId, @ApiIgnore HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        planService.acceptMember(notificationAndPlanId, userDto.getUserId());
        return ResponseEntity.ok(
                ResponseDto.createResponse(PlanResponseMessage.ACCEPT_MEMBER_SUCCESS.getMessage()));
    }
}
