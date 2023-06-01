package com.ssafy.triends.domain.notification.controller;

import com.ssafy.triends.domain.notification.constant.NotificationResponseMessage;
import com.ssafy.triends.domain.notification.service.NotificationService;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/notification")
@Api(tags = {"Notification"})
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    @PostMapping("/plan")
    @LoginRequired
    @ApiOperation(value = "플랜 멤버 초대 전송 (LoginRequired)", notes = "플랜 멤버 초대 알림을 전송한다.")
    @ApiImplicitParam(name = "receiverAndPlanAndUserId",
            value = "userId : 로그인한 유저의 pk \n receiverId : 초대를 보낼 유저의 pk \n planId : 초대할 플랜의 pk",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":2,\"receiverId\":5,\"planId\":3}")
    public ResponseEntity<ResponseDto<?>> sendPlanNotification(
            @RequestBody Map<String, Object> receiverAndPlanAndUserId)
            throws Exception {
        notificationService.sendPlanMemberInvitation(receiverAndPlanAndUserId);
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.CREATE_MEMBER_INVITATION_SUCCESS.getMessage()));
    }

    @PostMapping("/friend")
    @LoginRequired
    @ApiOperation(value = "친구 추가 요청 전송 (LoginRequired)", notes = "친구 요청 알림을 전송한다.")
    @ApiImplicitParam(name = "receiverAndUserId",
            value = "userId : 로그인한 유저의 pk \n receiverId : 요청을 보낼 유저의 pk",
            dataTypeClass = Map.class,
            defaultValue = "{\"userId\":2,\"receiverId\":5}")
    public ResponseEntity<ResponseDto<?>> sendFriendNotification(@RequestBody Map<String, Object> receiverAndUserId)
            throws Exception {
        notificationService.sendFriendRequest(receiverAndUserId);

        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.CREATE_FRIEND_REQUEST_SUCCESS.getMessage()));
    }

    @GetMapping("")
    @LoginRequired
    @ApiOperation(value = "알림 목록 조회 (LoginRequired)", notes = "현재 로그인한 사용자가 수신한 알림을 조회한다.")
    @ApiImplicitParam(name = "userId", value = "로그인한 유저의 pk", required = true, defaultValue = "2", dataTypeClass = Integer.class)
    public ResponseEntity<ResponseDto<?>> getAllNotifications(int userId)
            throws Exception {
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.SEARCH_NOTIFICATIONS_SUCCESS.getMessage(),
                notificationService.getAllNotifications(userId)));
    }

    @DeleteMapping("")
    @LoginRequired
    @ApiOperation(value = "알림 삭제 (LoginRequired)", notes = "선택한 알림을 삭제한다.")
    @ApiImplicitParam(name = "notificationId",
            value = "삭제할 알림의 pk",
            dataTypeClass = Integer.class,
            defaultValue = "5")
    public ResponseEntity<ResponseDto<?>> deleteOneNotification(int notificationId) throws Exception {
        notificationService.deleteOneNotification(notificationId);
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.DELETE_NOTIFICATION_SUCCESS.getMessage()));
    }

}
