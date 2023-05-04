package com.ssafy.triends.domain.notification.controller;

import com.ssafy.triends.domain.notification.service.NotificationService;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.global.dto.ResponseDto;
import org.apache.catalina.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.Map;

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
	public ResponseEntity<ResponseDto<?>> sendPlanNotification(@RequestParam Map<String, String> userAndSenderAndPlanId, HttpSession session) throws Exception {
		String userId = getUserIdForString(session);
		userAndSenderAndPlanId.put("userId", userId);

//		=== 테스트 ===
//		userAndSenderAndPlanId.put("userId", "3");
//		=============
		notificationService.sendPlanMemberInvitation(userAndSenderAndPlanId);
		return ResponseEntity.ok(ResponseDto.createResponse("플랜 멤버 초대 완료"));
	}

	@PostMapping("/friend")
	public ResponseEntity<ResponseDto<?>> sendFriendNotification(@RequestParam Map<String, String> userAndSenderId, HttpSession session) throws Exception {
		String userId = getUserIdForString(session);
		userAndSenderId.put("userId", userId);

//		=== 테스트 ===
//		userAndSenderId.put("userId", "3");
//		=============

		notificationService.sendFriendRequest(userAndSenderId);

		return ResponseEntity.ok(ResponseDto.createResponse("친구 요청 완료"));
	}

	@GetMapping("")
	public ResponseEntity<ResponseDto<?>> getAllNotifications(HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		return ResponseEntity.ok(ResponseDto.createResponse("알림 목록 조회 완료", notificationService.getAllNotifications(userDto.getUserId())));

//		=== 테스트 ===
//		return ResponseEntity.ok(ResponseDto.createResponse("알림 목록 조회 완료", notificationService.getAllNotifications(3)));
//		=============
	}

	@DeleteMapping("/{notificationId}")
	public ResponseEntity<ResponseDto<?>> deleteOneNotification(@PathVariable("notificationId") String notificationId) throws Exception {
		notificationService.deleteOneNotification(Integer.parseInt(notificationId));
		return ResponseEntity.ok(ResponseDto.createResponse("알림 삭제 완료"));
	}


	private String getUserIdForString(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute("userDto");
		return Integer.toString(userDto.getUserId());
	}
}
