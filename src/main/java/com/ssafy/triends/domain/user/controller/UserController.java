package com.ssafy.triends.domain.user.controller;

import com.ssafy.triends.domain.comment.model.CommentDto;
import com.ssafy.triends.domain.user.constant.UserResponseMessage;
import com.ssafy.triends.domain.user.model.UserDto;
import com.ssafy.triends.domain.user.service.UserService;
import com.ssafy.triends.global.constant.SessionDataName;
import com.ssafy.triends.global.dto.ResponseDto;
import com.ssafy.triends.global.interceptor.LoginRequired;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
	private final Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;

	@Autowired
	public UserController(UserService userService) {
		super();
		this.userService = userService;
	}

	@PostMapping("/login")
	@CrossOrigin(origins="http://localhost:8081")
	public ResponseEntity<?> loginUser(@RequestParam Map<String, Object> map, HttpSession session){
		Map<String, String> param=new HashMap<String, String>();
		System.out.println(map);
		param.put("userId", map.get("userId").toString());
		param.put("userPwd", map.get("userPwd").toString());
		try {
			UserDto userDto=userService.loginUser(param);
			session.setAttribute(SessionDataName.USER_INFO.getName(), userDto);
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.LOGIN_SUCCESS.getMessage(), userDto));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}

	@PostMapping("/logout")
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> logoutUser(HttpSession session){
		session.invalidate();
		return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.LOGOUT_SUCCESS.getMessage()));
	}
	
	@GetMapping
	@LoginRequired
	public ResponseEntity<ResponseDto<?>> getUser(HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
			return ResponseEntity.ok(ResponseDto.createResponse(UserResponseMessage.GET_USER_INFO.getMessage()));
		} catch (Exception e) {
			return exceptionHandling(e);
		}
	}
	
	@PostMapping
	@CrossOrigin(origins="http://localhost:8081")
	public ResponseEntity<ResponseDto<?>> joinUser( UserDto userDto){
		System.out.println(userDto);
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
	public ResponseEntity<?> modifyUser(UserDto userDto){
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
	public ResponseEntity<?> getComment(HttpSession session){
		try {
			UserDto sessionDto=(UserDto)session.getAttribute(SessionDataName.USER_INFO.getName());
			int userId=sessionDto.getUserId();
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
	public ResponseEntity<ResponseDto<?>> getPreferences(HttpSession session) throws Exception {
		UserDto userDto = (UserDto) session.getAttribute(SessionDataName.USER_INFO.getName());

		return ResponseEntity.ok(
				ResponseDto.createResponse(
						UserResponseMessage.GET_PREFERENCE.getMessage(),
						userService.getOneUserPreferences(userDto.getUserId())
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
	
	private ResponseEntity<ResponseDto<?>> exceptionHandling(Exception e) {
		e.printStackTrace();
		return (ResponseEntity<ResponseDto<?>>) ResponseEntity.notFound();
	}
}
