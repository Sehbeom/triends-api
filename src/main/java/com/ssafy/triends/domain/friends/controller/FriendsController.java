package com.ssafy.triends.domain.friends.controller;

import com.ssafy.triends.domain.friends.constant.FriendsResponseMessage;
import com.ssafy.triends.domain.friends.service.FriendsService;
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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/friends")
public class FriendsController {
	private final Logger logger = LoggerFactory.getLogger(FriendsController.class);
	private FriendsService friendsService;

	public FriendsController(FriendsService friendsService) {
		super();
		this.friendsService = friendsService;
	}

	@GetMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> friendsList(HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						FriendsResponseMessage.GET_FRIENDS_LIST_SUCCESS.getMessage(),
						friendsService.getFriendsList(userDto.getUserId())
				)
		);
	}

	@PostMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> acceptFriend(@RequestParam Map<String, Object> notificationAndSenderId, HttpSession session)
			throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
		friendsService.acceptFriend(notificationAndSenderId, userDto.getUserId());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						FriendsResponseMessage.ACCEPT_FRIEND_SUCCESS.getMessage()
				)
		);
	}

	@DeleteMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> deleteFriend(@RequestParam int friendId, HttpSession session)
			throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
		friendsService.deleteFriend(friendId, userDto.getUserId());

		return ResponseEntity.ok(ResponseDto.createResponse(
				FriendsResponseMessage.DELETE_FRIEND_SUCCESS.getMessage()
		));
	}
}
