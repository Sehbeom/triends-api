package com.ssafy.triends.domain.user.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.constant.UserResponseMessage;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.service.UserService;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.error.exception.ExceptionMessage;
import com.ssafy.triends.global.interceptor.LoginRequired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.ssafy.triends.global.util.jwt.JwtOperator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	private JwtOperator jwtOperator;

	@Autowired
	public UserController(UserService userService, JwtOperator jwtOperator) {
		super();
		this.userService = userService;
		this.jwtOperator = jwtOperator;
	}

	@PostMapping("/login")
	public ResponseEntity<?> loginUser(@RequestBody Map<String, String> idAndPassword) throws Exception {
		UserDto userDto = userService.loginUser(idAndPassword);
		logger.debug("user ID : {}", userDto.getUserId());

		if (userDto != null) {
			String accessToken = jwtOperator.createAccessToken("userId", userDto.getUserId());
			String refreshToken = jwtOperator.createRefreshToken("userId", userDto.getUserId());
			userService.saveRefreshToken(userDto.getUserId(), refreshToken);

			Map<String, Object> responseData = new HashMap<>();
			responseData.put("access-token", accessToken);
			responseData.put("refresh-token", refreshToken);
			responseData.put("userInfo", userDto);

			return ResponseEntity.ok(
					ResponseDto.createResponse(UserResponseMessage.LOGIN_SUCCESS.getMessage(), responseData)
			);
		} else {
			return ResponseEntity.accepted().body(ResponseDto.createResponse(UserResponseMessage.LOGIN_FAIL.getMessage()));
		}
	}

	@GetMapping("/logout")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> logoutUser(int userId) throws Exception {
		userService.removeRefreshToken(userId);
		return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.LOGOUT_SUCCESS.getMessage()));
	}
	
	@GetMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> getUser(int userId) throws Exception {
		return ResponseEntity.ok(
				ResponseDto.createResponse(UserResponseMessage.GET_USER_INFO.getMessage(),
						userService.getUser(userId)));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDto<?>> joinUser(@RequestBody UserDto userDto){
		try {
			userService.joinUser(userDto);
			List<UserDto> list=userService.userList();
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.JOIN_USER.getMessage(), list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PutMapping
	@LoginRequired
	public ResponseEntity<?> modifyUser(@RequestBody UserDto userDto){
		try {
			userService.modifyUser(userDto);
			List<UserDto> list=userService.userList();
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.MODIFY_USER_INFO.getMessage(), list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@GetMapping("/comment")
	@LoginRequired
	public ResponseEntity<?> getComment(int userId){
		try {
			List<CommentDto> list=userService.getComment(userId);
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.GET_USER_COMMENT.getMessage(), list));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@DeleteMapping("/comment/{commentId}")
	@LoginRequired
	public ResponseEntity<?> deleteComment(@PathVariable("commentId") int commentId){
		try {
			userService.deleteComment(commentId);
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.DELETE_USER_COMMENT.getMessage(), commentId));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@GetMapping("/preference")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> getPreferences(int userId) throws Exception {
		return ResponseEntity.ok(
				ResponseDto.createResponse(
						UserResponseMessage.GET_PREFERENCE.getMessage(),
						userService.getOneUserPreferences(userId)
				)
		);
	}

	@PostMapping("/preference")
	@LoginRequired
	public ResponseEntity<?> registPreference(@RequestBody Map<String, Object> preferenceIds, HttpSession session)
			throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
		userService.registPreferences(preferenceIds, userDto.getUserId());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						UserResponseMessage.REGISTER_PREFERENCE.getMessage(),
						userService.getOneUserPreferences(userDto.getUserId())
				)
		);
	}

	@PutMapping("/preference")
	@LoginRequired
	public ResponseEntity<?> modifyPreferences(@RequestBody Map<String, Object> preferenceIds, HttpSession session)
			throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
		userService.modifyPreferences(preferenceIds, userDto.getUserId());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						UserResponseMessage.MODIFY_PREFERENCE.getMessage(),
						userService.getOneUserPreferences(userDto.getUserId())
				)
		);
	}

	@DeleteMapping("/preference")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> deletePreferences(HttpSession session) {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());
		userService.deletePreference(userDto.getUserId());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						UserResponseMessage.DELETE_PREFERENCE.getMessage()
				)
		);
	}

	@GetMapping("/authorization")
	public ResponseEntity<ResponseDto<?>> authorization(int userId, HttpServletRequest request) throws Exception {
		String accessToken = request.getHeader("access-token");
		if (accessToken == null) {
			return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.USERINFO_NOT_FOUND.getMessage()));
		}

		if (jwtOperator.checkToken(accessToken)) {
			UserDto userDto = userService.getUser(userId);
			return ResponseEntity.ok(
					ResponseDto.createResponse(
							UserResponseMessage.AUTHORIZATION_SUCCESS.getMessage(),
							userDto
					)
			);
		}

		return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.TOKEN_EXPIRED.getMessage()));
	}

	@GetMapping("/authorization/refresh")
	public ResponseEntity<ResponseDto<?>> reissueAccessToken(int userId, HttpServletRequest request) throws Exception {
		String refreshToken = request.getHeader("refresh-token");

		if (refreshToken == null) {
			return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.USERINFO_NOT_FOUND.getMessage()));
		}

		String originRefreshToken = userService.getRefreshToken(userId);
		if (originRefreshToken.equals(refreshToken)) {
			UserDto userDto = userService.getUser(userId);
			String accessToken = jwtOperator.createAccessToken("userId", userId);

			Map<String, Object> tokenAndUserInfo = new HashMap<>();
			tokenAndUserInfo.put("access-token", accessToken);
			tokenAndUserInfo.put("userInfo", userDto);

			return ResponseEntity.ok(
					ResponseDto.createResponse(UserResponseMessage.ACCESS_TOKEN_REISSUE_SUCCESS.getMessage(),
							tokenAndUserInfo)
			);
		}

		return ResponseEntity.accepted().body(ResponseDto.createResponse(ExceptionMessage.TOKEN_EXPIRED.getMessage()));
	}

	private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
		e.printStackTrace();
		return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
	}
}
