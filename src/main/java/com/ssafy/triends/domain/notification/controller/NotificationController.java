package com.ssafy.triends.domain.notification.controller;

import com.ssafy.triends.domain.notification.constant.NotificationResponseMessage;
import com.ssafy.triends.domain.notification.service.NotificationService;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/notification")
public class NotificationController {

    private final Logger logger = LoggerFactory.getLogger(NotificationController.class);

    private NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        super();
        this.notificationService = notificationService;
    }

    @PostMapping("/plan")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> sendPlanNotification(
            @RequestParam Map<String, Object> receiverAndPlanId, HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        notificationService.sendPlanMemberInvitation(receiverAndPlanId, userDto.getUserId());
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.CREATE_MEMBER_INVITATION_SUCCESS.getMessage()));
    }

    @PostMapping("/friend")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> sendFriendNotification(
            @RequestParam int receiverId, HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        notificationService.sendFriendRequest(receiverId, userDto.getUserId());

        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.CREATE_FRIEND_REQUEST_SUCCESS.getMessage()));
    }

    @GetMapping("")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> getAllNotifications(HttpSession session)
            throws Exception {
        UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.SEARCH_NOTIFICATIONS_SUCCESS.getMessage(),
                notificationService.getAllNotifications(userDto.getUserId())));
    }

    @DeleteMapping("/{notificationId}")
    @LoginRequired
    public ResponseEntity<ResponseDto<?>> deleteOneNotification(
            @PathVariable("notificationId") String notificationId) throws Exception {
        notificationService.deleteOneNotification(Integer.parseInt(notificationId));
        return ResponseEntity.ok(ResponseDto.createResponse(
                NotificationResponseMessage.DELETE_NOTIFICATION_SUCCESS.getMessage()));
    }

}
